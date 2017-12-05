package com.example.form

import java.util.Locale

import com.example.form.build.MyFormDefinitionBuildInfo
import com.uxforms.domain.constraint.Required.required
import com.uxforms.domain.{FormDefinition, FormDefinitionFactory, ResourceBundleMessages}
import com.uxforms.dsl.containers.mustache.Section.section
import com.uxforms.dsl.helpers.FormDefinitionHelper._
import com.uxforms.dsl.widgets.Input.inputText
import com.uxforms.dsl.widgets.RadioGroup.radioGroup
import com.uxforms.dsl.widgets.asRow
import com.uxforms.domain.constraint.FixedChoice.fixedChoice
import com.uxforms.dsl.widgets.Content

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

        Content("<h2>Select address</h2>"),
        Content("<h4>Please check your address has been entered correctly. If your address is not available for selection please call us.</h4>"),


        inputText("houseName", "houseName.label" -> "House name or number", required),
        inputText("postCode", "postCode.label" -> "Postcode", required),

        Content("<h2>About the property</h2>"),

        inputText("elementName", "elementName.label" -> "What type of property is it?", required),
        inputText("elementName", "elementName.label" -> "How many bedrooms in the property?", required),
        inputText("elementName", "elementName.label" -> "How many bathrooms are there?", required),
        Content("<h4>Include bathrooms, en-suites and any rooms with a bath/shower or a toilet.</h4>"),
        inputText("elementName", "elementName.label" -> "When was the property built?", required),
        radioGroup("lodgers", "lodgers.label" -> "Does the property have a garage or outbuildings (including sheds)?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),
        inputText("elementName", "elementName.label" -> "What type of heating does the property have?", required),
        radioGroup("lodgers", "lodgers.label" -> "Do you have an intruder alarm at the property?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),

        Content("<h2>Building construction</h2>"),

        inputText("elementName", "elementName.label" -> "What are the external walls built of?", required),
        inputText("elementName", "elementName.label" -> "What is your roof made of?", required),
        radioGroup("lodgers", "lodgers.label" -> "Is any part of your roof flat?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),
        Content("<h4>Please include any garages or outbuildings when answering this question.</h4>"),
        radioGroup("lodgers", "lodgers.label" -> "Is the property used for business purposes?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "Is the property a listed building?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "Is there building work in progress at the property?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),


        Content("<h2>People at the Property</h2>"),

        inputText("elementName", "elementName.label" -> "Number of adults at the property?", required),
        inputText("elementName", "elementName.label" -> "Number of children under 18 years at the property?", required),
        radioGroup("lodgers", "lodgers.label" -> "Are there any lodgers at the property?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "Does anyone at the property smoke?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),
        inputText("elementName", "elementName.label" -> "Who is to be covered in the property?", required),
        inputText("elementName", "elementName.label" -> "Who owns the property?", required),
        inputText("elementName", "elementName.label" -> "Number of years at the property?", required),
        radioGroup("lodgers", "lodgers.label" -> "Will the property be left unoccupied for more than 30 consecutive days e.g. holidays?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "Do you or any other occupants of your home have any unspent or pending criminal convictions?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "Are you or any other occupants of your home currently bankrupt or making payments under an Individual Voluntary Agreement (IVA)?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "Have you or any other occupants of your home had any insurance policy cancelled, refused, declared void or had special terms applied?", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow),

        Content("<h2>Common statements</h2>"),
        Content("<h4>Below are some common statements about many homes in the UK, please read these carefully and adjust if needed. Your property...</h4>"),

        radioGroup("lodgers", "lodgers.label" -> "is in a good state of repair", required() ++ fixedChoice("yesOptions"), "yesOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "is situated more than 400 metres from the nearest water course (coast, rivers, brooks or streams)", required() ++ fixedChoice("yesOptions"), "yesOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "has not been flooded in the last 25 years, including the land that the property stands on", required() ++ fixedChoice("yesOptions"), "yesOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "has no trees taller than 10m within 5m of the property", required() ++ fixedChoice("yesOptions"), "yesOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "has not suffered from subsidence, heave, or landslip in the last 25 years", required() ++ fixedChoice("yesOptions"), "yesOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "has not previously been underpinned", required() ++ fixedChoice("yesOptions"), "yesOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "has no solar panels", required() ++ fixedChoice("yesOptions"), "yesOptions", asRow),
        radioGroup("lodgers", "lodgers.label" -> "Please confirm that the statements above accurately reflect the property you wish to insure	YES NO", required() ++ fixedChoice("yesNoOptions"), "yesNoOptions", asRow)

      ),
      section(
        "secondSectionMessages",

        inputText("elementName", "elementName.label" -> "Is any part of your roof flat?", required),
        inputText("elementName", "elementName.label" -> "Is any part of your roof flat?", required)


      )

    )

  }

}
