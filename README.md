# karaf-cassandra-mapping

This is a project to test cassandra mapping driver from datastax within a OSGI environment [Apache Karaf 4.1](https://karaf.apache.org/download.html).

For test this project you can do:

	1. Download karaf 4.0.4 or apache-karaf-4.1.0-SNAPSHOT
	4. Start cassandra database engine it is tested with cassandra 3.9
	3. Start karaf -> cd ${karaf}/bin/; ./karaf
	4. Add the repo of this project with this karaf command: feature:repo-add mvn:io.twim/karaf-features/1.0-SNAPSHOT/xml/features
	5. Install the feature created for this project: feature:install cassandra-installer
	6. Execute the command for test this problem: cassandra
	   The keyspace (twim_test) must be created with db.cql script
	   > ./cqlsh < bd.cql
	