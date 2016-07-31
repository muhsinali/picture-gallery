# --- Insert places

# --- !Ups

insert into place (id, name, country, description, picture, content_type) values (1, 'London', 'United Kingdom', 'This is a description', FILE_READ('./public/images/places/london.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (2, 'Paris', 'France', 'This is a description', FILE_READ('./public/images/places/paris.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (3, 'Dubai', 'United Arab Emirates', 'This is a description', FILE_READ('./public/images/places/dubai.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (4, 'Stockholm', 'Sweden', 'This is a description', FILE_READ('./public/images/places/stockholm.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (5, 'Nerja', 'Spain', 'This is a description', FILE_READ('./public/images/places/nerja.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (6, 'Amsterdam', 'Netherlands', 'This is a description', FILE_READ('./public/images/places/amsterdam.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (7, 'Singapore', 'Singapore', 'This is a description', FILE_READ('./public/images/places/singapore.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (8, 'Tokyo', 'Japan', 'This is a description', FILE_READ('./public/images/places/tokyo.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (9, 'San Francisco', 'United States of America', 'This is a description', FILE_READ('./public/images/places/sanFrancisco.jpg'), 'image/jpeg');



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

delete from place;

SET REFERENTIAL_INTEGRITY TRUE;

