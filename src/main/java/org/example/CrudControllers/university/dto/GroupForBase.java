package org.example.CrudControllers.university.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.example.CrudControllers.university.api.IGroup;

import java.io.Serializable;

@JsonDeserialize(builder = GroupForBase.Builder.class)
public class GroupForBase implements Serializable, IGroup {
    private static final long serialVersionUID = 1L;
    private final Long groupId;
    private final  String groupName;

    private GroupForBase(Long groupId, String groupName) {
        this.groupName = groupName;
        this.groupId = groupId;
    }


    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder{
        private Long groupId;
        private String groupName;

        private Builder(){
        }

        public Builder setGroupId(Long groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder setGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public static Builder create(){
            return new Builder();
        }

        public GroupForBase build(){
            return new GroupForBase(groupId, groupName);
        }
    }


    public Long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }
}
