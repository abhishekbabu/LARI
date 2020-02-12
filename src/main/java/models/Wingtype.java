package src.main.java.models;

public enum Wingtype {

    Unspecified {
        public String toString() {
            return "Unspecified";
        }
    },
    FixedWing {
        public String toString() {
            return "Fixed Wing";
        }
    },
    Quad {
        public String toString() {
            return "Quad";
        }
    },
    Octo {
        public String toString() {
            return "Octo";
        }
    },
    None {
        public String toString() {
            return "None";
        }
    };

    public Wingtype getWingtype(String wingtypeString) {
        Wingtype wingtype;
        switch(wingtypeString) {
            case "Unspecified":
                wingtype = Unspecified;
                break;
            case "Fixed Wing":
                wingtype = FixedWing;
                break;
            case "Quad":
                wingtype = Quad;
                break;
            case "Octo":
                wingtype = Octo;
                break;
            default:
                wingtype = None;
                break;
        }
        return wingtype;
    }

}
