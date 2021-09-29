use springbootweb122020;

insert into role(code,name) values('ADMIN','Quản trị hệ thống');
insert into role(code,name) values('USER','người dùng');

insert into users(username,password,fullname,status)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','admin',1);
insert into users(username,password,fullname,status)
values('nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van a',1);

INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);


-- ===================for database advance=======================
INSERT INTO `building` VALUES
                              (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_1',NULL,500,NULL,NULL,NULL,NULL,NULL,NULL,'Nam Giao Building Tower',NULL,2,NULL,NULL,15,'15 triệu/m2',NULL,'TANG_TRET,NGUYEN_CAN',NULL,'59 phan xích long',NULL,'Phường 2',NULL)
    ,(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_2',NULL,650,NULL,NULL,NULL,NULL,NULL,NULL,'ACM Tower',NULL,2,NULL,NULL,18,'18 triệu/m2',NULL,'NGUYEN_CAN',NULL,'96 cao thắng',NULL,'Phường 4',NULL)
    ,(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_1',NULL,200,NULL,NULL,NULL,NULL,NULL,NULL,'Alpha 2 Building Tower',NULL,1,NULL,NULL,20,'20 triệu/m2',NULL,'NOI_THAT',NULL,'153 nguyễn đình chiểu',NULL,'Phường 6',NULL),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_4',NULL,200,NULL,NULL,NULL,NULL,NULL,NULL,'IDD 1 Building',NULL,1,NULL,NULL,12,'12 triệu/m2',NULL,'TANG_TRET,NGUYEN_CAN,NOI_THAT',NULL,'111 Lý Chính Thắng',NULL,'Phường 7',NULL),(14,'nguyenvana','2021-09-28 15:56:19','nguyenvana','2021-09-28 15:56:19',NULL,'','',NULL,'',NULL,'QUAN_1','',NULL,'',NULL,'','',NULL,NULL,'demo rent 100','',NULL,'','',NULL,'','',NULL,'','','','',''),(15,'nguyenvana','2021-09-28 16:00:00','nguyenvana','2021-09-28 16:00:00',NULL,'','',NULL,'',NULL,'QUAN_3','',NULL,'',NULL,'','',NULL,NULL,'demo 200','',NULL,'','',NULL,'','',NULL,'','','','',''),(16,'nguyenvana','2021-09-28 16:19:36','nguyenvana','2021-09-28 16:19:36',NULL,'','',NULL,'',NULL,'QUAN_2','',NULL,'',NULL,'','',NULL,NULL,'đasa','',NULL,'','',NULL,'','',NULL,'','','','',''),(17,'nguyenvana','2021-09-28 16:33:21','nguyenvana','2021-09-28 16:33:21',NULL,'','',NULL,'',NULL,'QUAN_2','',NULL,'',NULL,'','',NULL,NULL,'23433','',NULL,'','',NULL,'','',NULL,'','','','',''),(18,NULL,NULL,'nguyenvana','2021-09-28 20:29:54',NULL,'','',NULL,'',NULL,'QUAN_1','',NULL,'',NULL,'','',NULL,NULL,'Lê Quang Như','',NULL,'','',NULL,'','',NULL,'','20','','',''),(19,'nguyenvana','2021-09-28 21:22:08','nguyenvana','2021-09-28 21:22:08',NULL,'','',NULL,'',NULL,'QUAN_2','',NULL,'',NULL,'','232',NULL,NULL,'Lê Quang Như','',NULL,'','',NULL,'','',NULL,'','20','','',''),(20,'nguyenvana','2021-09-28 21:23:34','nguyenvana','2021-09-28 21:23:34',NULL,'','',NULL,'',NULL,'QUAN_2','',NULL,'',NULL,'','',NULL,NULL,'','',NULL,'','',NULL,'','',NULL,'','','','',''),(21,'nguyenvana','2021-09-28 21:29:58','nguyenvana','2021-09-28 21:29:58',NULL,'','',NULL,'',NULL,'QUAN_2','',NULL,'',NULL,'','',NULL,NULL,'demoFi','',NULL,'','',NULL,'','','TANG_TRET,NGUYEN_CAN','','','','',''),(23,'nguyenvana','2021-09-28 22:23:17','nguyenvana','2021-09-28 22:23:17',NULL,'','',NULL,'',NULL,'QUAN_1','',NULL,'',NULL,'','',NULL,NULL,'demoGiaoToaNha','',NULL,'','',NULL,'','','','','','','','');

INSERT INTO `user` VALUES
                          (1,NULL,NULL,NULL,NULL,NULL,'nguyen van a','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvana')
    ,(2,NULL,NULL,NULL,NULL,NULL,'nguyen van b','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvanb')
    ,(3,NULL,NULL,NULL,NULL,NULL,'nguyen van c','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvanc')
    ,(4,NULL,NULL,NULL,NULL,NULL,'nguyen van d','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvand');

INSERT INTO `assignmentbuilding` VALUES
                                        (39,NULL,NULL,NULL,NULL,4,3)
    ,(40,NULL,NULL,NULL,NULL,3,3)
    ,(41,NULL,NULL,NULL,NULL,4,4)
    ,(45,'nguyenvana','2021-09-23 17:12:17','nguyenvana','2021-09-23 17:12:17',2,4)
    ,(47,'nguyenvana','2021-09-23 17:18:04','nguyenvana','2021-09-23 17:18:04',1,2)
    ,(52,NULL,NULL,NULL,NULL,1,3);


INSERT INTO `rentarea` VALUES (1,NULL,NULL,NULL,NULL,100,1),(2,NULL,NULL,NULL,NULL,200,1),(4,NULL,NULL,NULL,NULL,300,2),(5,NULL,NULL,NULL,NULL,400,2),(6,NULL,NULL,NULL,NULL,300,3),(7,NULL,NULL,NULL,NULL,400,3),(8,NULL,NULL,NULL,NULL,500,3),(9,NULL,NULL,NULL,NULL,100,4),(10,NULL,NULL,NULL,NULL,400,4),(11,NULL,NULL,NULL,NULL,250,4),(25,'nguyenvana','2021-09-28 16:46:51','nguyenvana','2021-09-28 16:46:51',100,18),(32,'nguyenvana','2021-09-28 20:32:09','nguyenvana','2021-09-28 20:32:09',200,18),(33,'nguyenvana','2021-09-28 20:32:09','nguyenvana','2021-09-28 20:32:09',500,18),(34,'nguyenvana','2021-09-28 20:32:09','nguyenvana','2021-09-28 20:32:09',600,18),(35,'nguyenvana','2021-09-28 21:22:08','nguyenvana','2021-09-28 21:22:08',100,19),(36,'nguyenvana','2021-09-28 21:22:08','nguyenvana','2021-09-28 21:22:08',200,19);

INSERT INTO `role` VALUES (1,NULL,NULL,NULL,NULL,'MANAGER','Quản lý'),(2,NULL,NULL,NULL,NULL,'STAFF','Nhân viên');

INSERT INTO `user_role` VALUES (1,1),(2,2),(3,2),(4,2);






