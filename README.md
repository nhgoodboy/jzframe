# jzframe
基于springboot实现的通用后台管理系统。前端参看vue-admin-template项目
见 http://104.199.198.38:9529

------------------------------------------------------------------

#简介： 前端基于vue，后端基于spring boot的通用后台管理系统。 功能包括登录、用户管理、角色权限管理、部门管理、个人主页。

技术上

1、前端是基于vue的vue-admin-template模版。自行开发了系统管理功能。实现了动态角色权限配置。

2、后端是基于springboot。实现了数据的增删查改（mybatis、springdatajpa、mysql）、图片上传及小图裁剪处理（lmax-disrupter）、权限管理（shiro）、前 后端数据通信（jwt）、项目持续集成部署（git、jenkins、docker）、缓存处理（redis）。
