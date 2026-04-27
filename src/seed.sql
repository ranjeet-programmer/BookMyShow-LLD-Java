INSERT INTO city(name, created_at, updated_at) VALUES
                                                   ('c1', NOW(), NOW()),
                                                   ('c2', NOW(), NOW());

INSERT INTO theator (name, address, city_id, created_at, updated_at) VALUES
                                                                         ('T1', "A1", 1, NOW(), NOW()),
                                                                         ('T2', "A2", 2, NOW(), NOW());


INSERT INTO auditorium (name, capacity, theator_id, created_at, updated_at) VALUES
                                                                                ('Screen 1', 200, 1, NOW(), now() ),
                                                                                ('Screen 2', 150, 1, NOW(), NOW() ),
                                                                                ('Screen 1', 180, 2, NOW(), NOW() );


INSERT INTO seat (seat_number, row_value, col_value, seat_type, auditorium_id, created_at, updated_At) VALUES
                                                                                                           ('A1', 1, 1, 0, 1, NOW(), NOW()),
                                                                                                           ('A2', 1, 2, 0, 1, NOW(), NOW()),
                                                                                                           ('B1', 2, 1, 1, 2, NOW(), NOW()),
                                                                                                           ('B2', 2, 2, 1, 2, NOW(), NOW());


INSERT INTO movie(name, poster, created_at, updated_at) VALUES
                                                            ("AVENGER ENDGAME","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVgaeV7d7Zv9YyyRzK8RiM-KxkZW1Z5aHyMA&s",Now(),Now()),
                                                            ("Inception", "Inception.jpg", NOW(), NOW());


INSERT INTO shows (movie_id, start_time, end_time, auditorium_id, created_at, updated_at) VALUES
                                                                                              (1, '2025-03-15 14:00:00', '2025-03-15 16:30:00', 1, NOW(), NOW()),
                                                                                              (1, '2025-03-15 18:00:00', '2025-03-15 21:00:00', 2, NOW(), NOW());


INSERT INTO show_seat (show_id, seat_id, status, created_at, updated_at) VALUES
                                                                             (1, 1, 0, NOW(), NOW()),
                                                                             (1, 2, 1, NOW(), NOW()),
                                                                             (2, 3, 0, NOW(), NOW()),
                                                                             (2, 4, 2, NOW(), NOW());
ALTER TABLE ticket DROP FOREIGN KEY FKbwyawliihwegfkw344gphbq3t;
ALTER TABLE ticket drop column show_seat_id;