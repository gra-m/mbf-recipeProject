# Current commit status:

# Spring 5 Spring Guru Recipe Project 
Following the course, updating where needed but not coding along to video. Once a
concept is known I pause the video and create tests/ code myself. Only copy pasting
template.html.

Org: Gra-m

Original By: John Thompson

On: 28/03/2022

### What was new for me?
*see petclinic for earlier examples of techniques not used before*
* Exception Handling:
  * @ResponseStatus custom exception -> which HTTP status is thrown globally #78
  * @ExceptionHandler -> @Controller level #79


### Project resources used or useful

[Markdown Cheat Sheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet "Adam P")

[Stack Edit](https://stackedit.io "31/08")

[Dillinger](https://dillinger.io "until you are off of visible page..")

[RudeComment-PossInterestingRefactorExercise](https://github.com/Celebes/spring-boot-pet-clinic/blob/master/pet-clinic-data/src/main/java/guru/springframework/sfgpetclinic/services/jpa/AbstractJpaService.java)

Table of Contents:

1. [Exception Handling](#1)
2. [Heading 2](#2)

   a. [Sub Heading a](#2a)
3. [Heading 3](#3)
4. [Heading 4](#4)

<a id="1"></a>
Exception Handling:
* @ResponseStatus custom exception -> which HTTP status is thrown globally #78
* @ExceptionHandler -> @Controller level #79
* Interface: HandlerExceptionResolver
  * ExceptionHandlerExceptionResolver -> matches uncaught exceptions to @ExceptionHandler
  * ResponseStatusExceptionResolver -> looks for uncaught exceptions matching @ResponseStatus
  * DefaultHandlerExceptionResolver -> (Internal to SpringMvc) converts standard Spring exceptions to HTTP status codes
* Just HTTP status == @ResponseStatus
* Redirection to view needed = SimpleMappingExceptionResolver
* both? == @ExceptionHandler on controller

<a id="2"></a>
Heading 2:

<a id="2a"></a>
SubHeading 2a:

<a id="3"></a>
Heading 3:

<a id="4"></a>
Heading 4:
    
