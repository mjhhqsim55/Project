drop table if exists activity cascade ;
drop table if exists activity_prods cascade ;
drop table if exists banner cascade ;
drop table if exists buy_items cascade ;
drop table if exists category cascade ;
drop table if exists category_prods cascade ;
drop table if exists image_entity cascade ;
drop table if exists menu cascade ;
drop table if exists prod cascade ;
drop table if exists prod_image cascade ;
drop table if exists role cascade ;
drop table if exists role_menu cascade ;
drop table if exists shop_car cascade ;
drop table if exists shop_car_buy_items cascade ;
drop table if exists user cascade ;
drop table if exists user_role cascade ;
drop sequence activity_st if exists;
drop sequence banner_st if exists;
drop sequence buy_items_st if exists;
drop sequence category_st if exists;
drop sequence image_entity_st if exists;
drop sequence prod_st if exists;
drop sequence shop_car_st if exists;
drop sequence user_st if exists;
create sequence activity_st start with 10 increment by 1;
create sequence banner_st start with 10 increment by 1;
create sequence buy_items_st start with 10000 increment by 1;
create sequence category_st start with 10 increment by 1;
create sequence image_entity_st start with 10 increment by 1;
create sequence prod_st start with 100 increment by 1;
create sequence shop_car_st start with 10000 increment by 1;
create sequence user_st start with 10000 increment by 1;
create table activity (id integer not null, name varchar(255), primary key (id));
create table activity_prods (activity_id integer not null, prods_pid integer not null unique);
create table banner (id integer not null, prod_pid integer unique, primary key (id));
create table buy_items (bid integer not null, count integer not null, is_pay boolean not null, prod_pid integer, date timestamp(6), number_item varchar(255), primary key (bid));
create table category (cid integer not null, category_name varchar(255), primary key (cid));
create table category_prods (category_cid integer not null, prods_pid integer not null unique);
create table image_entity (id integer not null, image_name varchar(255), path varchar(255), primary key (id));
create table menu (mid integer not null, mname varchar(255), name varchar(255), primary key (mid))
create table prod (pid integer not null, price float(53) not null, stock integer not null, category varbinary(255), name varchar(255), presentation varchar(255), primary key (pid));
create table prod_image (images_id integer not null, prod_pid integer not null);
create table role (rid integer not null, name varchar(255), rname varchar(255), primary key (rid));
create table role_menu (menus_mid integer not null, role_rid integer not null, primary key (menus_mid, role_rid));
create table shop_car (id integer not null, primary key (id));
create table shop_car_buy_items (buy_items_bid integer not null unique, shop_car_id integer not null);
create table user (is_enable boolean not null, shop_car_id integer unique, uid integer not null, register_date timestamp(6), e_mail varchar(255), password varchar(255), username varchar(255), primary key (uid));
create table user_role (roles_rid integer not null, user_uid integer not null, primary key (roles_rid, user_uid));

alter table activity_prods add constraint FKfbdc5vbgcchl9t15lxl74rhj9 foreign key (prods_pid) references prod;
alter table activity_prods add constraint FKcmmcypgmmqlhe3auqluxem8ry foreign key (activity_id) references activity;
alter table banner add constraint FK5hlmspm0m5pir2m1kd9ic747g foreign key (prod_pid) references prod;
alter table buy_items add constraint FKophppf1wrcokso2nbrbbws8jq foreign key (prod_pid) references prod;
alter table category_prods add constraint FK9qt3f9481j1lbaqom56amhnqe foreign key (prods_pid) references prod;
alter table category_prods add constraint FKph5hqv0w82tpwiyxccoea0ix6 foreign key (category_cid) references category;
alter table prod_image add constraint FKlxo2xqh8c3dqifl6wnhu6tfjr foreign key (images_id) references image_entity;
alter table prod_image add constraint FKdkhi46xasxhov1chxjmuit9sq foreign key (prod_pid) references prod;
alter table role_menu add constraint FKn12ja9dj7ya630pxilsm1sjt2 foreign key (menus_mid) references menu;
alter table role_menu add constraint FKedxqmoiuytb7as9o27r4v7oay foreign key (role_rid) references role;
alter table shop_car_buy_items add constraint FK8fljdp1yq8kjlm6d0e0arc3wk foreign key (buy_items_bid) references buy_items;
alter table shop_car_buy_items add constraint FK90m3vdwhw2du0nem8a0o6uv47 foreign key (shop_car_id) references shop_car;
alter table user add constraint FKgbvhsllm3d0nsf2dtuak7fjc0 foreign key (shop_car_id) references shop_car;
alter table user_role add constraint FKealp1l0rf2hltsfn7vsy19wns foreign key (roles_rid) references role;
alter table user_role add constraint FK90jh50muahvb84bfbekakgah8 foreign key (user_uid) references user;

 
INSERT INTO menu (mid , mname , name) VALUES (1002 , 'USER_BUGITEMS' , 'USER_BUGITEMS') ;  
INSERT INTO menu (mid , mname , name) VALUES (1004 , 'USER_BUGITEMS' , 'USER_BUGITEMS') ; 
INSERT INTO menu (mid , mname , name) VALUES (1006 , 'USER_USER_MESSAGE' , 'USER_USER_MESSAGE') ; 
INSERT INTO menu (mid , mname , name) VALUES (1008 , 'USER_HISTORY' , 'USER_HISTORY') ; 
INSERT INTO menu (mid , mname , name) VALUES (1102 , 'MANAGER_USER_ROLE' , 'MANAGER_USER_ROLE') ; 
INSERT INTO menu (mid , mname , name) VALUES (1104 , 'MANAGER' , 'MANAGER') ; 

INSERT INTO role (rid , rname , name) VALUES (1002 , 'USER' , 'USER') ; 
INSERT INTO role (rid , rname , name) VALUES (1004 , 'MANAGER' , 'MANAGER') ; 

INSERT INTO role_menu (role_rid , menus_mid) VALUES (1002 , 1002 ) ; 
INSERT INTO role_menu (role_rid , menus_mid) VALUES (1002 , 1004 ) ; 
INSERT INTO role_menu (role_rid , menus_mid) VALUES (1002 , 1006 ) ; 
INSERT INTO role_menu (role_rid , menus_mid) VALUES (1002 , 1008 ) ; 
INSERT INTO role_menu (role_rid , menus_mid) VALUES (1004 , 1102 ) ; 
INSERT INTO role_menu (role_rid , menus_mid) VALUES (1004 , 1104 ) ; 

INSERT INTO USER (UID , USERNAME  , PASSWORD , E_MAIL , REGISTER_DATE , IS_ENABLE ) VALUES (0 , 'root', 'im55',  'root@im.com' , CURRENT_TIMESTAMP , 1) ; 
INSERT INTO USER (UID , USERNAME  , PASSWORD , E_MAIL , REGISTER_DATE , IS_ENABLE) VALUES (1 , 'im', 'im55',  'im@im.com' , CURRENT_TIMESTAMP , 1) ; 
INSERT INTO USER_ROLE (USER_UID  , ROLES_RID) VALUES (0 , 1004) ; 
INSERT INTO USER_ROLE (USER_UID  , ROLES_RID) VALUES (0 , 1002) ; 
INSERT INTO USER_ROLE (USER_UID  , ROLES_RID) VALUES (1 , 1002) ; 

INSERT INTO "IMAGE_ENTITY" ( "ID", "IMAGE_NAME", "PATH" ) VALUES (1 , 'ps4.png', '/img') ; 
INSERT INTO "IMAGE_ENTITY" ( "ID", "IMAGE_NAME", "PATH" ) VALUES (2 , 'ps5.png', '/img') ; 
INSERT INTO "IMAGE_ENTITY" ( "ID", "IMAGE_NAME", "PATH" ) VALUES (3 , 'switch.png', '/img') ; 
INSERT INTO "IMAGE_ENTITY" ( "ID", "IMAGE_NAME", "PATH" ) VALUES (4 , 'xbox one.png', '/img') ; 
INSERT INTO "IMAGE_ENTITY" ( "ID", "IMAGE_NAME", "PATH" ) VALUES (5 , 'PS5_1.png', '/img') ; 


INSERT INTO "CATEGORY" ( "CID", "CATEGORY_NAME" ) VALUES (1 , '数码') ; 
INSERT INTO "CATEGORY" ( "CID", "CATEGORY_NAME" ) VALUES (2 , '电脑') ; 
INSERT INTO "CATEGORY" ( "CID", "CATEGORY_NAME" ) VALUES (3 , '办公') ; 
INSERT INTO "CATEGORY" ( "CID", "CATEGORY_NAME" ) VALUES (4 , '服饰') ; 
INSERT INTO "CATEGORY" ( "CID", "CATEGORY_NAME" ) VALUES (5 , '游戏主机') ; 
INSERT INTO "CATEGORY" ( "CID", "CATEGORY_NAME" ) VALUES (6 , '水果') ; 

INSERT INTO "PROD" ( "PID", "PRICE", "STOCK" , "NAME", "PRESENTATION" ) VALUES ( 1 ,1000 ,10 , 'ps4', 'ps4游戏主机') ; 
INSERT INTO "PROD" ( "PID", "PRICE", "STOCK" , "NAME", "PRESENTATION" ) VALUES ( 2 ,4000 ,10 , 'ps5', 'ps5游戏主机') ; 
INSERT INTO "PROD" ( "PID", "PRICE", "STOCK" , "NAME", "PRESENTATION" ) VALUES ( 3 ,1200 ,10 , 'switch', 'switch') ; 
INSERT INTO "PROD" ( "PID", "PRICE", "STOCK" , "NAME", "PRESENTATION" ) VALUES ( 4 ,2000 ,10 , 'xbox one', 'xbox one') ; 
INSERT INTO "PROD" ( "PID", "PRICE", "STOCK" , "NAME", "PRESENTATION" ) VALUES ( 5 ,4000 ,10 , 'ps5', 'ps5游戏主机') ; 
INSERT INTO "PROD" ( "PID", "PRICE", "STOCK" , "NAME", "PRESENTATION" ) VALUES ( 6 ,4000 ,10 , 'ps5', 'ps5游戏主机') ; 
INSERT INTO "PROD" ( "PID", "PRICE", "STOCK" , "NAME", "PRESENTATION" ) VALUES ( 7 ,4000 ,10 , 'ps5', 'ps5游戏主机') ; 
INSERT INTO "PROD" ( "PID", "PRICE", "STOCK" , "NAME", "PRESENTATION" ) VALUES ( 8 ,4000 ,10 , 'ps5', 'ps5游戏主机') ; 

INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (1 , 1) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (2 , 2) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (5 , 2) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (3 , 3) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (4 , 4) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (2 , 5) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (5 , 5) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (2 , 6) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (5 , 6) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (2 , 7) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (5 , 7) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (2 , 8) ; 
INSERT INTO "PROD_IMAGE" ( "IMAGES_ID", "PROD_PID" ) VALUES (5 , 8) ; 

INSERT INTO "CATEGORY_PRODS" ( "CATEGORY_CID", "PRODS_PID" ) VALUES (5 , 1) ; 
INSERT INTO "CATEGORY_PRODS" ( "CATEGORY_CID", "PRODS_PID" ) VALUES (5 , 2) ; 
INSERT INTO "CATEGORY_PRODS" ( "CATEGORY_CID", "PRODS_PID" ) VALUES (5 , 3) ; 
INSERT INTO "CATEGORY_PRODS" ( "CATEGORY_CID", "PRODS_PID" ) VALUES (5 , 4) ; 
INSERT INTO "CATEGORY_PRODS" ( "CATEGORY_CID", "PRODS_PID" ) VALUES (5 , 5) ; 
INSERT INTO "CATEGORY_PRODS" ( "CATEGORY_CID", "PRODS_PID" ) VALUES (5 , 6) ; 
INSERT INTO "CATEGORY_PRODS" ( "CATEGORY_CID", "PRODS_PID" ) VALUES (5 , 7) ; 
INSERT INTO "CATEGORY_PRODS" ( "CATEGORY_CID", "PRODS_PID" ) VALUES (5 , 8) ; 


INSERT INTO "ACTIVITY" ( "ID", "NAME" ) VALUES (1 , '游戏主机特惠') ; 
INSERT INTO "ACTIVITY" ( "ID", "NAME" ) VALUES (2 , '电脑特惠') ; 

INSERT INTO "ACTIVITY_PRODS" ( "ACTIVITY_ID", "PRODS_PID" ) VALUES (1 , 1) ; 
INSERT INTO "ACTIVITY_PRODS" ( "ACTIVITY_ID", "PRODS_PID" ) VALUES (1 , 2) ; 
INSERT INTO "ACTIVITY_PRODS" ( "ACTIVITY_ID", "PRODS_PID" ) VALUES (1 , 3) ; 
INSERT INTO "ACTIVITY_PRODS" ( "ACTIVITY_ID", "PRODS_PID" ) VALUES (1 , 4) ; 
INSERT INTO "ACTIVITY_PRODS" ( "ACTIVITY_ID", "PRODS_PID" ) VALUES (1 , 5) ; 

INSERT INTO "BANNER" ( "ID", "PROD_PID" ) VALUES (1 , 1) ; 
INSERT INTO "BANNER" ( "ID", "PROD_PID" ) VALUES (2 , 2) ; 
INSERT INTO "BANNER" ( "ID", "PROD_PID" ) VALUES (3 , 3) ; 
INSERT INTO "BANNER" ( "ID", "PROD_PID" ) VALUES (4 , 4) ; 
INSERT INTO "BANNER" ( "ID", "PROD_PID" ) VALUES (5 , 5) ; 



