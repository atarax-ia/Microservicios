#!/bin/bash
DIR_FILES=Provincias/provincias/src/main/resources
cat $DIR_FILES/schema.sql >> init.sql
cat $DIR_FILES/data.sql >> init.sql

DIR_FILES=Localidades/localidades/src/main/resources
cat $DIR_FILES/schema.sql >> init.sql
cat $DIR_FILES/data.sql >> init.sql