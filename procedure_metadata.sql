Preparando a amostra 


Step 1 :
#########################################################################################################################################
#############insere no início####################################################

INSERT INTO metadata (qt_compartilhada, tag, link, mesmo_link)  
SELECT COUNT(tagini), tagini, linktag, COUNT(linktag) FROM metainicio GROUP BY tagini;


#########insere no metasorte###################################################
INSERT INTO metasort (iniuser, folkssort, tagsort, usersort, gruposort)  
select DISTINCT metainicio.idmetainicio, metadata.qt_compartilhada, metadata.tag, metainicio.user, metainicio.grupo_id from metadata INNER JOIN metainicio ON metadata.tag = metainicio.tagini WHERE metadata.qt_compartilhada > (SELECT AVG(qt_compartilhada) from metadata);
################################################################################

##Esse insere todos os grupos com o metadado escolhido##########################
INSERT INTO grupotag(folks, tags, grupo) 
select folkssort, tagsort, gruposort from metasort WHERE tagsort = 'iphone' order by folkssort desc;
################################################################################

######Aqui seria uma alternativa futura, caso não ter o número de metadados sufucinete para média.###
   
INSERT INTO grupotag(folks, tags, grupo) 
select folkssort, tagsort, gruposort from metasort order by folkssort desc;


################################################################################


SELECT COUNT(tagini), tagini, linktag, COUNT(linktag) FROM metainicio GROUP BY tagini;


#############seleciona ps metadados pela folksonomia############################################
INSERT INTO metatags (grupo, folksmax, tags) 
select DISTINCT m.gruposort, m.folkssort, m.tagsort  from metasort as m INNER JOIN grupotag as tg
ON m.gruposort = tg.grupo group by m.tagsort order by m.folkssort  desc;
####################opção de cima################################################################
select DISTINCT m.gruposort, m.folkssort, m.tagsort  from metasort as m INNER JOIN grupotag as tg
ON m.gruposort = tg.grupo order by m.folkssort, m.tagsort desc;


#################################################################################################
##########################################################################################################################################



###############apagar#########################

   SET SQL_SAFE_UPDATES = 0;
   delete from grupo;
   SET SQL_SAFE_UPDATES = 1;

   SET SQL_SAFE_UPDATES = 0;
   delete from metainicio;
   SET SQL_SAFE_UPDATES = 1;
   
   SET SQL_SAFE_UPDATES = 0;
   delete from metadata;
   SET SQL_SAFE_UPDATES = 1;
   
   SET SQL_SAFE_UPDATES = 0;
   delete from metasort;
   SET SQL_SAFE_UPDATES = 1;
   
   SET SQL_SAFE_UPDATES = 0;
   delete from grupotag;
   SET SQL_SAFE_UPDATES = 1;
   
   SET SQL_SAFE_UPDATES = 0;
   delete from metatags;
   SET SQL_SAFE_UPDATES = 1;
   
   SET SQL_SAFE_UPDATES = 0;
   delete from metric;
   SET SQL_SAFE_UPDATES = 1;
   
   SET SQL_SAFE_UPDATES = 0;
   delete from metric;
   SET SQL_SAFE_UPDATES = 1;
   
   SET SQL_SAFE_UPDATES = 0;
   delete from memoriarelativa;
   SET SQL_SAFE_UPDATES = 1;
   
   SET SQL_SAFE_UPDATES = 0;
   delete from memoriaindividual;
   SET SQL_SAFE_UPDATES = 1;

##############################################













