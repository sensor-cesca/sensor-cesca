CREATE DATABASE arduino;
\connect arduino
CREATE TABLE dht_sensor (
	collection_timestamp timestamp,
	humidity real,
	temperature real
);