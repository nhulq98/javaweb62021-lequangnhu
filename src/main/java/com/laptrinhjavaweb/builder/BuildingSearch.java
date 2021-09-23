package com.laptrinhjavaweb.builder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class BuildingSearch {
    private final String name;
    private final String street;
    private final String ward;
    private final String district;
    private final String floorArea;
    private final Integer numberOfBasement;
    private final List<String> rentTypes;
    private final Integer rentPriceFrom;
    private final Integer rentPriceTo;
    private final Integer rentAreaFrom;
    private final Integer rentAreaTo;
    private final String direction;
    private final String level;
    private final String managerName;
    private final String managerPhone;
    private final Long staffId;

    public BuildingSearch(Builder builder) {
        this.name = builder.name;
        this.district = builder.district;
        this.floorArea = builder.floorArea;
        this.numberOfBasement = builder.numberOfBasement;
        this.street = builder.street;
        this.ward = builder.ward;
        this.rentTypes = builder.rentTypes;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.rentAreaFrom = builder.rentAreaFrom;
        this.rentAreaTo = builder.rentAreaTo;
        this.staffId = builder.staffId;
        this.direction = builder.direction;
        this.level = builder.level;
        this.managerName = builder.managerName;
        this.managerPhone = builder.managerPhone;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {

        private String name;
        private String district;
        private String floorArea;
        private String street;
        private String ward;
        private Integer numberOfBasement;
        private String direction;
        private List<String> rentTypes;
        private Integer rentPriceFrom;
        private Integer rentPriceTo;
        private Integer rentAreaFrom;
        private Integer rentAreaTo;
        private Long staffId;
        private String level;
        private String managerName;
        private String managerPhone;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setFloorArea(String floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setRentTypes(List<String> rentTypes) {
            this.rentTypes = rentTypes;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setRentPriceFrom(Integer rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Builder setRentPriceTo(Integer rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Builder setRentAreaFrom(Integer rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }

        public Builder setRentAreaTo(Integer rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }

        public BuildingSearch build() {
            return new BuildingSearch(this);
        }

    }
}
