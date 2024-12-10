import React from "react";
import RegistrationPatient from "../../components/registration/RegistrationPatient";

const RegistrationPage = () => {
    return (
        <div style={styles.container}>
            <RegistrationPatient />
        </div>
    );
};

const styles = {
    container: {
        maxWidth: "500px",
        margin: "50px auto",
        padding: "20px",
        border: "1px solid #ddd",
        borderRadius: "8px",
        boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
        backgroundColor: "#fff",
    },
};

export default RegistrationPage;
