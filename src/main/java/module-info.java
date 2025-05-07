module tech.ixirsii.clash4j {
    requires static lombok;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.module.paramnames;
    requires okhttp3;
    requires org.slf4j;
    requires reactor.core;

    exports tech.ixirsii.clash;
    exports tech.ixirsii.clash.data;
    exports tech.ixirsii.clash.data.capital;
    exports tech.ixirsii.clash.data.clan;
    exports tech.ixirsii.clash.data.goldpass;
    exports tech.ixirsii.clash.data.league;
    exports tech.ixirsii.clash.data.location;
    exports tech.ixirsii.clash.data.player;
    exports tech.ixirsii.clash.data.war;
    exports tech.ixirsii.clash.exception;
}
