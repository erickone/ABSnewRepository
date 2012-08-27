/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.DeliveryEntity;
import com.abs.siif.programming.entities.ValidationEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public class ValidationDto implements Serializable
{

  private DeliveryEntity delivery;
  private List<ComponentEntity> components;
  private List<String> questions;
  private List<ValidationEntity> validations;

  //Sección de Preguntas
  /**
   * @return the components
   */
  public List<ComponentEntity> getComponents()
  {
    return components;
  }

  /**
   * @param components the components to set
   */
  public void setComponents(List<ComponentEntity> components)
  {
    this.components = components;
  }

  /**
   * @return the questions
   */
  public List<String> getQuestions()
  {
    return questions;
  }

  /**
   * @param questions the questions to set
   */
  public void setQuestions(List<String> questions)
  {
    this.questions = questions;
  }

  /**
   * @return the validations
   */
  public List<ValidationEntity> getValidations()
  {
    return validations;
  }

  /**
   * @param validations the validations to set
   */
  public void setValidations(List<ValidationEntity> validations)
  {
    this.validations = validations;
  }

  public ArrayList<ValidationQuestionDto> listOfValSeplanQuestion()
  {
    ArrayList<ValidationQuestionDto> myQuestions = new ArrayList<ValidationQuestionDto>();
    String answerLE[] =
    {
      "L", "E"
    };

    ValidationQuestionDto myQuestion = new ValidationQuestionDto();

    myQuestion.setIndexQuestion(0);
    myQuestion.setQuestion("¿El propósito del proyecto se precisa de manera concreta a los productos entregables?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(1);
    myQuestion.setQuestion("El proyecto o proceso está alineado correctamente al programa?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(2);
    myQuestion.setQuestion("¿Está redactado de manera clara y correcta?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(3);
    myQuestion.setQuestion("¿Contribuye al logro del propósito del proyecto?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(4);
    myQuestion.setQuestion("¿Expresa un resultado o producto entregable?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(5);
    myQuestion.setQuestion("¿Está construido correctamente (metodología)?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(6);
    myQuestion.setAnswer(answerLE);
    myQuestion.setQuestion("¿Mide un Logro(L) o un Esfuerzo(E)?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(7);
    myQuestion.setQuestion("¿Se precisa una unidad de medida verificable?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(8);
    myQuestion.setQuestion("¿Está construido correctamente (metodología)?");
    myQuestions.add(myQuestion);

    return myQuestions;
  }

  public ArrayList<ValidationQuestionDto> listOfValSeplanQuestion(int elemento)
  {
    ArrayList<ValidationQuestionDto> myQuestions = new ArrayList<ValidationQuestionDto>();
    String answerLE[] =
    {
      "L", "E"
    };

    ValidationQuestionDto myQuestion = new ValidationQuestionDto();

    if (elemento == 1)
    {
      myQuestion.setIndexQuestion(0);
      myQuestion.setQuestion("¿El propósito del proyecto se precisa de manera concreta a los productos entregables?");
      myQuestions.add(myQuestion);

      myQuestion = new ValidationQuestionDto();
      myQuestion.setIndexQuestion(1);
      myQuestion.setQuestion("El proyecto o proceso está alineado correctamente al programa?");
      myQuestions.add(myQuestion);
    }

    if (elemento == 2)
    {
      myQuestion = new ValidationQuestionDto();
      myQuestion.setIndexQuestion(2);
      myQuestion.setQuestion("¿Está redactado de manera clara y correcta?");
      myQuestions.add(myQuestion);

      myQuestion = new ValidationQuestionDto();
      myQuestion.setIndexQuestion(3);
      myQuestion.setQuestion("¿Contribuye al logro del propósito del proyecto?");
      myQuestions.add(myQuestion);

      myQuestion = new ValidationQuestionDto();
      myQuestion.setIndexQuestion(4);
      myQuestion.setQuestion("¿Expresa un resultado o producto entregable?");
      myQuestions.add(myQuestion);
    }

    if (elemento == 3)
    {
      myQuestion = new ValidationQuestionDto();
      myQuestion.setIndexQuestion(5);
      myQuestion.setQuestion("¿Está construido correctamente (metodología)?");
      myQuestions.add(myQuestion);

      myQuestion = new ValidationQuestionDto();
      myQuestion.setIndexQuestion(6);
      myQuestion.setAnswer(answerLE);
      myQuestion.setQuestion("¿Mide un Logro(L) o un Esfuerzo(E)?");
      myQuestions.add(myQuestion);

      myQuestion = new ValidationQuestionDto();
      myQuestion.setIndexQuestion(7);
      myQuestion.setQuestion("¿Se precisa una unidad de medida verificable?");
      myQuestions.add(myQuestion);
    }

    if (elemento == 4)
    {
      myQuestion = new ValidationQuestionDto();
      myQuestion.setIndexQuestion(8);
      myQuestion.setQuestion("¿Está construido correctamente (metodología)?");
      myQuestions.add(myQuestion);
    }

    return myQuestions;
  }

  public ArrayList<ValidationQuestionDto> listOfSefinGeneralQuestions()
  {
    ArrayList<ValidationQuestionDto> myQuestions = new ArrayList<ValidationQuestionDto>();
    ValidationQuestionDto myQuestion = new ValidationQuestionDto();

    //Escribir preguntas generales
    myQuestion.setIndexQuestion(9);
    myQuestion.setQuestion("¿El encuadre programático es correcto?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(10);
    myQuestion.setQuestion("¿La calendarización de metas es congruente con la recursos?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(11);
    myQuestion.setQuestion("¿La valoración de costos del proceso se considera adecuada?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(12);
    myQuestion.setQuestion("¿Las partidas de gasto que se consideran son las apropiadas?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(13);
    myQuestion.setQuestion("¿Existe congruencia meta - presupuesto?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(14);
    myQuestion.setQuestion("¿La plantilla de personal tiene congruencia con la UEG y sus acciones?");
    myQuestions.add(myQuestion);

    myQuestion = new ValidationQuestionDto();
    myQuestion.setIndexQuestion(15);
    myQuestion.setQuestion("¿La clasificación de la ficha es correcta (Proceso/Proyecto)?");
    myQuestions.add(myQuestion);

    return myQuestions;
  }

  public ArrayList<String> generatedTitlesbyVathegory()
  {
    ArrayList<String> objListTitle = new ArrayList<String>(5);

    objListTitle.add("Propósito u Objetivo del Proyecto");
    objListTitle.add("Componentes");
    objListTitle.add("Indicadores");
    objListTitle.add("Metas");
    objListTitle.add("Observaciones");
    return objListTitle;
  }

  /**
   * @return the delivery
   */
  public DeliveryEntity getDelivery()
  {
    return delivery;
  }

  /**
   * @param delivery the delivery to set
   */
  public void setDelivery(DeliveryEntity delivery)
  {
    this.delivery = delivery;
  }
}
