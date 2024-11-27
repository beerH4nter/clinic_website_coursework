import React from "react";
import AddPatientForm from "./component/AddPatientForm";
import GetAllPatients from "./component/GetAllPatients";
import FindPatientById from "./component/FindPatientById";

function App() {
    return (
        <div>
            <AddPatientForm />
            <hr />
            <GetAllPatients />
            <hr />
            <FindPatientById />
        </div>
    );
}

export default App;
