# --- Insert places

# --- !Ups

insert into place (id, name, country, description, picture, content_type) values (1, 'London', 'United Kingdom', 'Swag', FILE_READ('./public/images/places/london.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (2, 'Paris', 'France', '', FILE_READ('./public/images/places/paris.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (3, 'Dubai', 'United Arab Emirates', '', FILE_READ('./public/images/places/dubai.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (4, 'Stockholm', 'Sweden', '', FILE_READ('./public/images/places/stockholm.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (5, 'Nerja', 'Spain', '', FILE_READ('./public/images/places/nerja.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (6, 'Amsterdam', 'Netherlands', '', FILE_READ('./public/images/places/amsterdam.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (7, 'Singapore', 'Singapore', '', FILE_READ('./public/images/places/singapore.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (8, 'Tokyo', 'Japan', '', FILE_READ('./public/images/places/tokyo.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (9, 'San Francisco', 'United States of America', '', FILE_READ('./public/images/places/sanFrancisco.jpg'), 'image/jpeg');



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

delete from place;

SET REFERENTIAL_INTEGRITY TRUE;

