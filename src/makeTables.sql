CREATE DATABASE news_all CHARACTER SET gbk COLLATE gbk_chinese_ci;

USE news_all;

CREATE TABLE admin
(
adminId int NOT NULL,
adminName varchar(20) NOT NULL,
adminPass varchar(32),
adminInfo text,
primary key(adminId)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

CREATE TABLE users
(
usersId int NOT NULL,
usersName varchar(20) NOT NULL,
usersPass varchar(32),
usersEmail varchar(50),
usersInfo text,
realName varchar(20),
sex varchar(10),
phone varchar(20),
idNumber varchar(30),
primary key(usersId)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

CREATE TABLE newstype
(
newsTypeId int NOT NULL,
newsTypeName varchar(200),
newsTypeDescripe text,
primary key(newsTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

CREATE TABLE newsinfo
(
newsInfoId int NOT NULL,
newsInfoTitle varchar(100),
newsInfoDescribe text,
newsInfoContent longtext,
newsInfoTime datetime,
newsAuthor varchar(40),
adminId int,
newsTypeId int,
newsInfoState int,
primary key(newsInfoId),
foreign key(adminId) references admin(adminId),
foreign key(newsTypeId) references newstype(newsTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

CREATE TABLE replys
(
replytId int NOT NULL,
replytContent longtext,
replytTime datetime,
usersId int,
newsInfoId int,
primary key(replytId),
foreign key(usersId) references users(usersId),
foreign key(newsInfoId) references newsinfo(newsInfoId)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

CREATE TABLE leavemessage
(
leaveMessageId int NOT NULL,
leaveMessageContent text,
leaveMessageTime datetime,
usersId int,
primary key(leaveMessageId),
foreign key(usersId) references users(usersId)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

CREATE TABLE `news_attachment` (
  `attachment_id` int(10) NOT NULL auto_increment,
  `news_id` int(10) default NULL,
  `attachment_name` varchar(50) default NULL,
  `attachment_content` longblob,
  UNIQUE KEY `attachment_id` (`attachment_id`),
  KEY `Relationship_1_FK` (`news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 AUTO_INCREMENT=3 ;

insert into admin(adminId,adminName,adminPass,adminInfo) values(1,"admin","21232F297A57A5A743894A0E4A801FC3","init");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(1,"Stock,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(2,"Fund,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(3,"Stock,Fund,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(4,"Bond,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(5,"Stock,Bond,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(6,"Fund,Bond,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(7,"Stock,Fund,Bond,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(8,"Forex,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(9,"Stock,Forex,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(10,"Fund,Forex,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(11,"Stock,Fund,Forex,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(12,"Bond,Forex,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(13,"Stock,Bond,Forex,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(14,"Fund,Bond,Forex,","");
insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) values(15,"Stock,Fund,Bond,Forex,","");