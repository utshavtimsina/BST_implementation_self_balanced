package main;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {

        PatientOperations patientOperations = new PatientOperations();
        BST root = patientOperations.loadBST();
        patientOperations.DisplayAllPatients(root);
        /**
         *   //Case 1
         *         patientOperations.insertNewPatient(root);
         */

        /**
         * //Case 2
         *
         *         patientOperations.deletePatientInformation(root);
         */

        /**Case 3
         *
         * edit a patient’s appointment;
         */
         patientOperations.editInfo(root);

         patientOperations.persistAll(root);
//        System.out.println("Balanceing the Tree and Displaying");
//        root =patientOperations.balanceTreeOnRequest(root);
//        patientOperations.DisplayAllPatients(root);


    }


}
