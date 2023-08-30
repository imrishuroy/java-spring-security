CREATE TABLE video_entity (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO video_entity (name, description) VALUES
('John Doe', 'This is John Doe'),
('Jane Doe', 'This is Jane Doe');