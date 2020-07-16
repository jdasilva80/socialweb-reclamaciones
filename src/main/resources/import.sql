INSERT INTO motivos(descripcion) values('motivo 1');
INSERT INTO motivos(descripcion) values('motivo 2');
INSERT INTO motivos(descripcion) values('motivo 3');
INSERT INTO motivos(descripcion) values('motivo 4');

INSERT INTO opciones(descripcion) values('opcion 1');
INSERT INTO opciones(descripcion) values('opcion 2');
INSERT INTO opciones(descripcion) values('opcion 3');
INSERT INTO opciones(descripcion) values('opcion 4');

INSERT INTO prioridades(descripcion) values('prioridad 1');
INSERT INTO prioridades(descripcion) values('prioridad 2');
INSERT INTO prioridades(descripcion) values('prioridad 3');
INSERT INTO prioridades(descripcion) values('prioridad 4');

INSERT INTO tipos(descripcion) values('tipo 1');
INSERT INTO tipos(descripcion) values('tipo 2');
INSERT INTO tipos(descripcion) values('tipo 3');
INSERT INTO tipos(descripcion) values('tipo 4');

INSERT INTO reclamaciones(descripcion, peticion, username, email, fecha_reclamacion, fecha, num_localizador, enviar_mail, motivo_id, prioridad_id, tipo_id, archivo ) VALUES ('Reclamación 1', '123.123,a', 'jdasilva1980', 'jdasilva@gmail.com',  NOW(),  NOW(), 1, true, 1, 1, 1, '')
INSERT INTO reclamaciones(descripcion, peticion, username, email, fecha_reclamacion, fecha, num_localizador, enviar_mail, motivo_id, prioridad_id, tipo_id, archivo ) VALUES ('Reclamación 2', '123.123,b', 'rosamaria67', 'rosa67@gmail.com',  NOW(),  NOW(), 2, false, 2, 2, 2, '')
INSERT INTO reclamaciones(descripcion, peticion, username, email, fecha_reclamacion, fecha, num_localizador, enviar_mail, motivo_id, prioridad_id, tipo_id, archivo ) VALUES ('Reclamación 3', '123.123,c', 'jdasilva1980', 'jdasilva@gmail.com', '12-01-20', '12-01-20', 2, false, 3, 3, 3, '')
INSERT INTO reclamaciones(descripcion, peticion, username, email, fecha_reclamacion, fecha, num_localizador, enviar_mail, motivo_id, prioridad_id, tipo_id, archivo ) VALUES ('Reclamación 4', '123.123,d', 'rosamaria67', 'rosa67@gmail.com', '12-01-20', '12-01-20', 2, true, 0, 4, 4, 4, '')
INSERT INTO reclamaciones(descripcion, peticion, username, email, fecha_reclamacion, fecha, num_localizador, enviar_mail, motivo_id, prioridad_id, tipo_id, archivo ) VALUES ('Reclamación 5', '123.123,e', 'jdasilva1980', 'jdasilva@gmail.com', '12-01-20', '12-01-20', 2, false, 1, 1, 1, '')
INSERT INTO reclamaciones(descripcion, peticion, username, email, fecha_reclamacion, fecha, num_localizador, enviar_mail, motivo_id, prioridad_id, tipo_id, archivo ) VALUES ('Reclamación 6', '123.123,f', 'rosamaria67', 'rosa67@gmail.com', '12-01-20', '12-01-20', 2, false, 2, 2, 2, '')
INSERT INTO reclamaciones(descripcion, peticion, username, email, fecha_reclamacion, fecha, num_localizador, enviar_mail, motivo_id, prioridad_id, tipo_id, archivo ) VALUES ('Reclamación 7', '123.123,g', 'jdasilva1980', 'jdasilva@gmail.com', '12-01-20', '12-01-20', 2, true, 3, 3, 3, '')

INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(1,1);
INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(1,3);

INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(2,1);
INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(2,3);

INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(3,1);
INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(3,2);

INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(4,1);
INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(4,3);

INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(5,1);
INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(5,2);

INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(6,1);
INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(6,3);

INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(7,1);
INSERT INTO reclamaciones_opciones(reclamacion_id, opcion_id) values(7,2);
