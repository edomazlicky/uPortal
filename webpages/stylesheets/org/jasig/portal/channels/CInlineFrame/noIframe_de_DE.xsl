<?xml version='1.0'?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

<xsl:param name="locale">de_DE</xsl:param>

<xsl:template match="iframe" >
  Dieser Browser unterstützt keine Inlineframes.<br/> 
  <a href="{url}" target="_blank">Klicken Sie hier um den Inhalt zu sehen</a> in einem weiteren Fanster.
</xsl:template>

</xsl:stylesheet>
