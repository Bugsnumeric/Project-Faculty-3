package org.example;
//Design patter Builder
public class Location {
    private String county;
    private Integer sirutaCode;

    private String locality;
    private String adminUnit;
    private String adress;
    private Integer latitude;
    private Integer longitude;

    public static class Builder {
        private String country;
        private Integer sirutaCode;

        private String locality = "";
        private String adminUnit = "";
        private String adress = "";
        private Integer latitude = null;
        private Integer longitude = null;

        public Builder(String country, Integer sirutaCode) {
            this.country = country;
            this.sirutaCode = sirutaCode;
        }

        public Builder setLocality(String locality) {
            this.locality = locality;
            return this;
        }

        public Builder setAdminUnit(String adminUnit) {
            this.adminUnit = adminUnit;
            return this;
        }

        public Builder setAdress(String adress) {
            this.adress = adress;
            return this;
        }

        public Builder setLatitude(Integer latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Integer longitude) {
            this.longitude = longitude;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }

    private Location(Builder builder) {
        county = builder.country;
        sirutaCode = builder.sirutaCode;
        locality = builder.locality;
        adminUnit = builder.adminUnit;
        adress = builder.adress;
        latitude = builder.latitude;
        longitude = builder.longitude;
    }
}
