
INSERT INTO Hardware (id, code, naziv, tip, kolicina, cijena) VALUES (1, 'XYZ', 'INTEL i7-9700', 'CPU', 2, 300.0);
INSERT INTO Hardware (id, code, naziv, tip, kolicina, cijena) VALUES (2, 'ASD', 'NVIDIA GTX 980 Ti', 'GPU', 1, 200.0);
INSERT INTO Hardware (id, code, naziv, tip, kolicina, cijena) VALUES (3, 'QWE', 'SAMSUNG 2667MHz 4GB', 'RAM', 3, 75.0);
INSERT INTO Hardware (id, code, naziv, tip, kolicina, cijena) VALUES (4, 'RTZ', 'KINGSTON SSD 250GB', 'STORAGE', 6, 55.0);


INSERT INTO Review (naslov, tekst, ocjena, hardware_id) VALUES ('Odlicno', 'Izvrsno iznad ocekivanja',
                                                                5, 1);
INSERT INTO Review (naslov, tekst, ocjena, hardware_id) VALUES ('Ocekivano', 'Bla bla ocekivano',
                                                                3, 1);
INSERT INTO Review (naslov, tekst, ocjena, hardware_id) VALUES ('Dobro', 'Lorem ipsum dobro',
                                                                4, 2);
INSERT INTO Review (naslov, tekst, ocjena, hardware_id) VALUES ('Odlicno', 'Izvrsno iznad ocekivanja',
                                                                5, 3);
INSERT INTO Review (naslov, tekst, ocjena, hardware_id) VALUES ('Prosjecno', 'Prosjek, nista posebno',
                                                                3, 4);



insert into user(id, username, password) values
(1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
(2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'), -- password = admin
(3, 'creator', '$2a$12$A4EUgRLxuAKWDSJIlyxE5upiJ8cN3hMrzbVxnWKyyM2hrTnTTtzfK'); -- password = creator


insert into authority (id, authority_name) values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_CREATOR');


insert into user_authority (user_id, authority_id) values
(1, 2),
(2, 1),
(3, 3);