package org.scalatra.example.forms

import org.scalatra._
import org.scalatra.i18n.I18nSupport
import org.scalatra.forms._

case class ValidationForm(name: String)

class FormsController extends ScalatraServlet with FormSupport with I18nSupport {

  val form = mapping(
    "name"     -> label("Text", text(required, maxlength(100))),
  )(ValidationForm.apply)

  get("/") {
    html.form()
  }

  post("/") {
    validate(form)(
      errors => BadRequest(html.form()),
      form   => html.result(form)
    )
  }

}
