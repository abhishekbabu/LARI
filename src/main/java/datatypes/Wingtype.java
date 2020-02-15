package src.main.java.datatypes;

/**
 * <b>Wingtype</b> represents a wingtype for an AFSLSystem
 */
public enum Wingtype {

    /**
     * Unspecified wingtype
     */
    Unspecified {
        public String toString() {
            return "Unspecified";
        }
    },
    /**
     * Fixed wing wingtype
     */
    FixedWing {
        public String toString() {
            return "FixedWing";
        }
    },
    /**
     * Quad wingtype
     */
    Quad {
        public String toString() {
            return "Quad";
        }
    },
    /**
     * Octo wingtype
     */
    Octo {
        public String toString() {
            return "Octo";
        }
    },
    /**
     * No wingtype
     */
    None {
        public String toString() {
            return "None";
        }
    };

}
