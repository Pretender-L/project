package com.project.demo.pojo

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tb_admin")
class Admin : Serializable {
    @Id
    var id: String = ""                         //ID
    var loginName: String? = null               //登陆名称
    var password: String? = null                //密码
    var status: String? = null                   //状态
}