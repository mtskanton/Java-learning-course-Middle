<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
               <h2>Text of the header</h2>
                <table border = '1'>
                    <tr bgcolor = "#777777">
                        <td>NAME</td>
                        <td>SURNAME</td>
                        <td>DATE</td>
                        <td>TITLE</td>
                        <td>TEXT</td>
                    </tr>
                    <xsl:for-each select="requests/request">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="surname"/></td>
                            <td><xsl:value-of select="date"/></td>
                            <td><xsl:value-of select="title"/></td>
                            <td><xsl:value-of select="text"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>