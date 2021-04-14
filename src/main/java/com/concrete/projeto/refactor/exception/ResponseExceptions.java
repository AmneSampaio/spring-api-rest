package com.concrete.projeto.refactor.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ResponseExceptions {
    private Integer status;
    private OffsetDateTime date;
    private String message;
    private List<Body> bodies;

    public static class Body {
        private String name;
        private String message;

        public Body(String name, String message) {
            this.setName(name);
            this.setMessage(message);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public ResponseExceptions(Integer status, OffsetDateTime date, String message, List<Body> bodies) {
        this.status = status;
        this.date = date;
        this.message = message;
        this.bodies = bodies;
    }

    public ResponseExceptions() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public void setBodies(List<Body> bodies) {
        this.bodies = bodies;
    }

}
