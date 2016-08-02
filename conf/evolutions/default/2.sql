# --- Insert places

# --- !Ups

insert into place (id, name, country, description, picture, content_type) values (1, 'London', 'United Kingdom', 'London is the capital and most populous city of the United Kingdom. On the River Thames, London has been a major settlement for two millennia. It was founded by the Romans, who named it Londinium.', FILE_READ('./public/images/places/london.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (2, 'Paris', 'France', 'Paris is the capital and most populous city of France. Situated on the river Seine in northern metropolitan France, it is in the centre of the Île-de-France region, also known as the région parisienne, "Paris Region".', FILE_READ('./public/images/places/paris.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (3, 'Dubai', 'United Arab Emirates', 'Dubai is the most populous city in the United Arab Emirates (UAE) and is the capital of the Emirate of Dubai, one of the seven emirates that make up the country. Dubai has emerged as a global city and business hub of the Middle East.', FILE_READ('./public/images/places/dubai.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (4, 'Stockholm', 'Sweden', 'Stockholm is the capital of Sweden and the most populous city in the Nordic countries, with 2.2 million people living in the metropolitan area. The city is spread across 14 islands on the coast in the southeast of Sweden.', FILE_READ('./public/images/places/stockholm.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (5, 'Nerja', 'Spain', 'Nerja is a resort town along southern Spain\’s Costa del Sol. Its seafront promenade, Balcón de Europa, tops a promontory with views of the Mediterranean and mountains. Below it lie sandy beaches and cliffside coves.', FILE_READ('./public/images/places/nerja.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (6, 'Amsterdam', 'Netherlands', 'Amsterdam is the Netherlands’ capital, known for its artistic heritage, elaborate canal system and narrow houses with gabled facades, legacies of the city’s 17th-century Golden Age.', FILE_READ('./public/images/places/amsterdam.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (7, 'Singapore', 'Singapore', 'Singapore, the world’s only island-city state, is a global financial centre with a tropical climate and multicultural population. It is also known for its cultural diversity and eclectic street fare.', FILE_READ('./public/images/places/singapore.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (8, 'Tokyo', 'Japan', 'Tokyo, Japan’s bustling capital, mixes the ultramodern and the traditional, from neon-lit skyscrapers and anime shops to cherry trees and temples. The city is famed for its vibrant food scene and its trendy teen fashion scene.', FILE_READ('./public/images/places/tokyo.jpg'), 'image/jpeg');
insert into place (id, name, country, description, picture, content_type) values (9, 'San Francisco', 'United States of America', 'San Francisco, in northern California, is a city on the tip of a peninsula surrounded by the Pacific Ocean and San Francisco Bay. It is known for its hilly landscape, year-round fog, iconic Golden Gate Bridge.', FILE_READ('./public/images/places/sanFrancisco.jpg'), 'image/jpeg');



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

delete from place;

SET REFERENTIAL_INTEGRITY TRUE;

