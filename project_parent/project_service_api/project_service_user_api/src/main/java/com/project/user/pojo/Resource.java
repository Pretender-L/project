package com.project.user.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * resource实体类
 */
@Table(name = "tb_resource")
public class Resource implements Serializable {

    @Id
    private Integer id;//id
    private String resKey;//res_key
    private String resName;//res_name
    private Integer parentId;//parent_id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return id.equals(resource.id) && resKey.equals(resource.resKey) && resName.equals(resource.resName) && parentId.equals(resource.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resKey, resName, parentId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

}
