package com.example.form

import java.util.Locale

import com.example.form.build.MyFormDefinitionBuildInfo
import com.uxforms.domain.constraint.Required.required
import com.uxforms.domain.{FormDefinition, FormDefinitionFactory, ResourceBundleMessages}
import com.uxforms.dsl.containers.mustache.Section.section
import com.uxforms.dsl.helpers.FormDefinitionHelper._
import com.uxforms.dsl.widgets.Input.inputText

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object MyFormDefinitionFactory extends FormDefinitionFactory with TemplateLoader {

  /**
    * This is which locales are supported by your form, in order of preference.
    * So if your user doesn't explicitly state which locale they want the form in, they
    * will get the first in this sequence.
    */
  override val supportedLocales: Seq[Locale] = Seq(Locale.UK)

  /**
    * Makes my classLoader available implicitly so that message bundles can be referenced
    * easily.
    */
  implicit val localClassLoader = getClass.getClassLoader


  /**
    * Factory method for instantiating your form definition.
    */
  override def formDefinition(requestedLocale: Locale)(implicit executionContext: ExecutionContext): FormDefinition = {

    /**
      * Resolves the locale requested by the user from a combination of their HTTP headers,
      * explicitly requested locale (i.e. in the URL), and those supported by this form definition.
      */
    implicit val locale = resolveRequestedLocale(requestedLocale)

    implicit val formLevelMessages: ResourceBundleMessages = "formMessages"

    /**
      * This is where the questions for your form are defined.
      */
    formDef(

      MyFormDefinitionBuildInfo.name,

      2 days,

      completedSection("completedMessages"),

      section(
        "firstSectionMessages",

        inputText("houseName", "houseName.label" -> "House name or number", required),
        inputText("postCode", "postCode.label" -> "Postcode", required),


        inputText("elementName", "elementName.label" -> "What type of property is it?", required),
        inputText("elementName", "elementName.label" -> "How many bedrooms in the property?", required),
        inputText("elementName", "elementName.label" -> "How many bathrooms are there?", required),
        inputText("elementName", "elementName.label" -> "When was the property built?", required),
        inputText("elementName", "elementName.label" -> "Does the property have a garage or outbuildings (including sheds)?", required),
        inputText("elementName", "elementName.label" -> "What type of heating does the property have?", required),
        inputText("elementName", "elementName.label" -> "Do you have an intruder alarm at the property?", required),

        inputText("elementName", "elementName.label" -> "What are the external walls built of?", required),
        inputText("elementName", "elementName.label" -> "What is your roof made of?", required),
        inputText("elementName", "elementName.label" -> "Is any part of your roof flat?", required),
        inputText("elementName", "elementName.label" -> "Is the property used for business purposes?", required),
        inputText("elementName", "elementName.label" -> "Is the property a listed building?", required),
        inputText("elementName", "elementName.label" -> "Is there building work in progress at the property?", required),

        inputText("elementName", "elementName.label" -> "Number of adults at the property?", required),
        inputText("elementName", "elementName.label" -> "Number of children under 18 years at the property?", required),
        inputText("elementName", "elementName.label" -> "Are there any lodgers at the property?", required),
        inputText("elementName", "elementName.label" -> "Does anyone at the property smoke?", required),
        inputText("elementName", "elementName.label" -> "Who is to be covered in the property?", required),
        inputText("elementName", "elementName.label" -> "Who owns the property?", required),
        inputText("elementName", "elementName.label" -> "Number of years at the property?", required),
        inputText("elementName", "elementName.label" -> "Will the property be left unoccupied for more than 30 consecutive days e.g. holidays?", required),
        inputText("elementName", "elementName.label" -> "Do you or any other occupants of your home have any unspent or pending criminal convictions?", required),
        inputText("elementName", "elementName.label" -> "Are you or any other occupants of your home currently bankrupt or making payments under an Individual Voluntary Agreement (IVA)?", required),
        inputText("elementName", "elementName.label" -> "Have you or any other occupants of your home had any insurance policy cancelled, refused, declared void or had special terms applied?", required)



      )

    )

  }

}
