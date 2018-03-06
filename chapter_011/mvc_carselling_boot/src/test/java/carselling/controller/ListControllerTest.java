package carselling.controller;

import carselling.logic.RepositoryAdvertisementCustom;
import carselling.logic.RepositoryBrand;
import carselling.model.Advertisement;
import carselling.model.Brand;
import carselling.model.Carcase;
import carselling.model.User;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс теста контроллера List.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(List.class)
public class ListControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RepositoryAdvertisementCustom rac;

    @MockBean
    private RepositoryBrand brands;

    private final Advertisement adBasic = new Advertisement();
    private final Advertisement adYesterday = new Advertisement();
    private final Advertisement adAnotherBrand = new Advertisement();

    @Before
    public void setAds() {
        adBasic.setId(1);
        adBasic.setBrand(new Brand(1));
        adBasic.setModel("model");
        adBasic.setCarcase(new Carcase(1));
        adBasic.setDate(new Timestamp(new Date().getTime()));
        adBasic.setUser(new User(1));

        adYesterday.setId(2);
        adYesterday.setBrand(new Brand(1));
        adYesterday.setModel("model");
        adYesterday.setCarcase(new Carcase(1));
        adYesterday.setDate(new Timestamp(new Date().getTime() - 24*60*60*1000));
        adYesterday.setUser(new User(1));

        adAnotherBrand.setId(3);
        adAnotherBrand.setBrand(new Brand(999));
        adAnotherBrand.setModel("model");
        adAnotherBrand.setCarcase(new Carcase(1));
        adAnotherBrand.setDate(new Timestamp(new Date().getTime()));
        adAnotherBrand.setUser(new User(1));
    }

    /**
     * Если запрос без фильтра, то получен общий список объявлений.
     * @throws Exception исключение
     */
    @Test
    @WithMockUser(username = "a", roles = "USER")
    public void whenRequestAds_thenGetThemListed() throws Exception {

        given(
            this.rac.getAllFiltered(null, null)
        ).willReturn(
            new ArrayList<>(
                    Lists.newArrayList(adBasic, adYesterday, adAnotherBrand)
            )
        );

        this.mvc.perform(
                get("/list").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("list")
        ).andExpect(
                model().attribute("ads", hasSize(3))
        ).andExpect(
                model().attribute("ads", hasItem(
                        allOf(
                                hasProperty("id", is(1))
                        )
                ))
        ).andExpect(
                model().attribute("ads", hasItem(
                        allOf(
                                hasProperty("id", is(2))
                        )
                ))
        ).andExpect(
                model().attribute("ads", hasItem(
                        allOf(
                                hasProperty("id", is(3))
                        )
                ))
        );

        verify(rac, times(1)).getAllFiltered(null, null);
        verifyNoMoreInteractions(rac);
    }

    /**
     * Если фильтрация запроса по бренду.
     * @throws Exception исключение
     */
    @Test
    @WithMockUser(username = "a", roles = "USER")
    public void whenRequestAdsFilteredByBrand_thenGetThemListed() throws Exception {

        given(
                this.rac.getAllFiltered(null, "1")
        ).willReturn(
                new ArrayList<>(
                        Lists.newArrayList(adBasic, adYesterday)
                )
        );

        this.mvc.perform(
                get("/list?brand=1").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("list")
        ).andExpect(
                model().attribute("ads", hasSize(2))
        ).andExpect(
                model().attribute("ads", hasItem(
                        allOf(
                                hasProperty("id", is(1))
                        )
                ))
        ).andExpect(
                model().attribute("ads", hasItem(
                        allOf(
                                hasProperty("id", is(2))
                        )
                ))
        );

        verify(rac, times(1)).getAllFiltered(null, "1");
        verifyNoMoreInteractions(rac);
    }

    /**
     * Если фильтрация объявлений за сегодня.
     * @throws Exception исключение
     */
    @Test
    @WithMockUser(username = "a", roles = "USER")
    public void whenRequestAdsFilteredFortoday_thenGetThemListed() throws Exception {

        given(
                this.rac.getAllFiltered("on", null)
        ).willReturn(
                new ArrayList<>(
                        Lists.newArrayList(adBasic, adAnotherBrand)
                )
        );

        this.mvc.perform(
                get("/list?today=on").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("list")
        ).andExpect(
                model().attribute("ads", hasSize(2))
        ).andExpect(
                model().attribute("ads", hasItem(
                        allOf(
                                hasProperty("id", is(1))
                        )
                ))
        ).andExpect(
                model().attribute("ads", hasItem(
                        allOf(
                                hasProperty("id", is(3))
                        )
                ))
        );

        verify(rac, times(1)).getAllFiltered("on", null);
        verifyNoMoreInteractions(rac);
    }

    /**
     * Редирект со стартовой страницы.
     * @throws Exception исключение
     */
    @Test@WithMockUser(username = "a", roles = "USER")
    public void whenRequestStartPage_thenRedirected() throws Exception {
        this.mvc.perform(
                get("/").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().is3xxRedirection()
        ).andExpect(
                redirectedUrl("/list")
        );
    }
}
