package com.example.form

import com.uxforms.dsl.{MustacheRenderEngine, RemoteTemplateResolver}

trait TemplateLoader {
  private val themeName = "uxforms-theme"
  implicit val renderEngine = new MustacheRenderEngine(new RemoteTemplateResolver(themeName, "templates"))
}
