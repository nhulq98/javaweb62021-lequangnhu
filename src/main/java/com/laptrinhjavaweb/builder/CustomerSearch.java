package com.laptrinhjavaweb.builder;

import lombok.Getter;

@Getter
public class CustomerSearch {
    private final String fullName;
    private final String phone;
    private final String email;
    private final Long staffId;

    public CustomerSearch(Builder builder) {
        this.fullName = builder.fullName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.staffId = builder.staffId;
    }

    public static class Builder {
        private String fullName;
        private String phone;
        private String email;
        private Long staffId;

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public CustomerSearch build() {
            return new CustomerSearch(this);
        }
    }
}
