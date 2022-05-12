# Current commit status: [![CircleCI](https://circleci.com/gh/gra-m/mbf-recipeProject/tree/main.svg?style=svg)](https://circleci.com/gh/gra-m/mbf-recipeProject/tree/main)

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
* [Using command or 'form' objects](https://docs.spring.io/spring-framework/docs/2.0.8/reference/mvc.html)


### Project resources used or useful

[Markdown Cheat Sheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet "Adam P")

[Stack Edit](https://stackedit.io "31/08")

[Dillinger](https://dillinger.io "until you are off of visible page..")

[RudeComment-PossInterestingRefactorExercise](https://github.com/Celebes/spring-boot-pet-clinic/blob/master/pet-clinic-data/src/main/java/guru/springframework/sfgpetclinic/services/jpa/AbstractJpaService.java)

[DataValidation Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Bean_Validation_Cheat_Sheet.html)
[@NotBlank Deprecated](https://hibernate.org/validator/documentation/)


Table of Contents:

1. [Exception Handling](#1)
2. [DOCKER](#2)

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
DOCKER:

Start a container:

    docker run -dp 3000:3000 docker/getting-started

Start Docker tutorial:

    docker run -d -p 80:80 docker/getting-started
Building

Dockerfile @ root of project

    FROM node:12-alpine
    # Adding build tools to make yarn install work on Apple silicon / arm64 machines
    RUN apk add --no-cache python2 g++ make
    WORKDIR /app
    COPY . .
    RUN yarn install --production
    CMD ["node", "src/index.js"]

Build docker image from the directory of its dockerfile:

    docker build -t getting-started .

where getting started is the tagname
-d what’s that?

    docker run -d <image name> #runs it as a background process.

List all docker images on system:

    docker images

Map host port to container port:

    docker run -p 8080:34224 <image name>
Removing

Get container id:

    docker ps

Stop container:

    docker stop container-id
    or 
    docker kill <container name>

Remove

    docker rm container-id

Stop and Remove 1 step:

    docker rm -f container-id

Tail (show last part of log)

    docker logs -f <container name>



Docker hub, create public repo
Create Repo to share:

untagged == fail:

    $ docker push [username]/getting-started
    #Refers to repo docker.io/[username]/getting-started
    Error: An image does not exist locally with the tag: [username]/getting/started

TAG FIRST:

    docker tag getting-started Gra8m/getting-started

THEN:

    docker push Gra8m/getting-started


Start container with a file named /data.txt with a random num 1-10000
docker run -d ubuntu bash -c "shuf -i 1-10000 -n 1 -o /data.txt && tail -f /dev/null"

see output, [exec]ute the container:

    docker exec container-id cat /data.txt

List contents of ubuntu file system

    docker run -it ubuntu ls /


Persisting data with a volume exercise:

Create a volume, to enable persistence between different containers of the same image

    docker volume create todo-db

Run container with -v flag specifying a volume to mount.

    docker run -dp 3000:3000 -v todo-db:/etc/todos gra8m/getting-started

Remove container for todo app:

    docker ps
    docker rm -f container-id


Inspect a volume:
docker volume inspect volume-name


    PS D:\OtherDesktop> docker volume inspect todo-db
    [
        {
            "CreatedAt": "2022-05-04T06:38:19Z",
            "Driver": "local",
            "Labels": {},
            "Mountpoint": "/var/lib/docker/volumes/todo-db/_data",
            "Name": "todo-db",
            "Options": {},
            "Scope": "local"
        }
    ]
    PS D:\OtherDesktop>


Use Binding Mounts with Dev-Mode containers to show live changes (next) for now going back to SF guru Java oriented tut.

SFG Tut commands:

Delete all Docker Images:

    docker rmi ${docker images -q}

Show all Docker Images

    docker images -a

Run Ubuntu Container

    docker run -it ubuntu bash


SFG Tut Clean up:

Kill all containers Working - tested May 11, 2022

    docker kill $(docker ps -q)

Delete all containers: Working - tested May 11, 2022

    docker rm $(docker ps -a -q)

Delete dangling images: Not working tested May 11, 2022
Editing and then naming a new image with an old name does not overwrite, it leaves a dangler.

    docker rmi $(docker images -q -f dangling=true) 

Delete all images: Not tested

    docker rmi $(docker images -q)

Delete a single image:

    docker rmi <image name>

Delete volumes:  Tested working May 11, 2022

    docker volume rm $(docker volume ls -f dangling=true -q)

See console output of a docker container:

    docker logs <container name>
How do you shell into a docker container?
docker exec -it <container name> bash
How do you share storage on the host sys with a docker container?
-v <host path>:<container path>
#Eg:
docker run -v <my host path>:<the container path> <image name>


MONGO

Will get latest:

    docker pull mongo

Will run in background with mapped port that we can access locally → 27017

    PS D:\> docker run -dp 27017:27017 mongo


Full tag: registry.hub.docker.com/mongo:latest

latest is default
registry.hub.docker.com/gra8m/image-name

Image IDs (SHA 12d built from layer SHAs) can be given a human readable tag:
TAG FIRST:

    docker tag getting-started Gra8m/getting-started

THEN:

    docker push Gra8m/getting-started


DB State where VOLUME is to be held as permanent DataBases:


    docker run -dp 27017:27017 -v test1:\docker_storage\mongo mongo

The above is creating test 1 but not able to specify D:\

RABBITMQ Docker

need management console:

    docker run -d --hostname my-rabbit --name second-rabbit -p 8080:15672 rabbitmq:3-management

standard login == guest guest

-e environment vars

Specify an environment var:

    docker run -e MY_VAR=my_prop <image name>
MYSQL Docker


    docker run --name some-mysql -v /HELLO/CANYOUFINDME/IWASMADE:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql:8.0
    
    docker run --name mysql-into-temp-question -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3306:3306 -d mysql:latest
    # tried the below from users/gra_m/pwd no sql file in folder (wind vs his osx?) b
    docker run --name some-mysql -v mySqlDb:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql:8.0




CENTOS image:
https://stackoverflow.com/questions/30209776/docker-container-will-automatically-stop-after-docker-run-d


Running centos linux image in -d means that it automatically closes, instead run:

     docker run -t -d centos/ubuntu
The following assumes you have bash installed on windows ‘winpty’ available:

Then bash into it -it is interactive mode: May 11, 2022 worked

     winpty docker exec -it strange_burnell bash
    [root@6ba86e333b2d /]# ls
    bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
    [root@6ba86e333b2d /]# ps -ef # shows what running
    UID        PID  PPID  C STIME TTY          TIME CMD
    root         1     0  0 19:34 pts/0    00:00:00 /bin/bash
    root        15     0  0 19:38 pts/1    00:00:00 bash
    root        30    15  0 19:41 pts/1    00:00:00 ps -ef
    [root@6ba86e333b2d /]#
    #DEADEND --> install of java via yum failed for centos. So repeated the above with ubuntu then:
    RUN apt update
    
    RUN apt-get install openjdk-11-jre




Ubuntu instead of Centos (see above):
https://stackoverflow.com/questions/61815233/install-java-runtime-in-debian-based-docker-image



<a id="2a"></a>
SubHeading 2a:

<a id="3"></a>
Heading 3:

<a id="4"></a>
Heading 4:
    
