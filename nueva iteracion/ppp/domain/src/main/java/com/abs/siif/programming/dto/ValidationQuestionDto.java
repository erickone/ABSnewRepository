/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import java.io.Serializable;

/**
 *
 * @author jacob.flores
 * Se encarga de generar las estructura para las preguntas
 */
public class ValidationQuestionDto implements Serializable
{
  private String theirQuestion;
  private int theirIndexQuestion; 

  private String answer[]={"Si","No"};
  
  /**
   * @return the theirIndexQuestion
   */
  public int getIndexQuestion() 
  {
    return theirIndexQuestion;
  }

  /**
   * @param theirIndexQuestion the theirIndexQuestion to set
   */
  public void setIndexQuestion(int anIndexQuestion) 
  {
    this.theirIndexQuestion = anIndexQuestion;
  }

  /**
   * @return the theirQuestion
   */
  public String getQuestion() 
  {
    return theirQuestion;
  }

  /**
   * @param theirQuestion the theirQuestion to set
   */
  public void setQuestion(String aQuestion) 
  {
    this.theirQuestion = aQuestion;
  }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }
    
    public String getAnswer(int index){
        return answer[index];
    }


}
