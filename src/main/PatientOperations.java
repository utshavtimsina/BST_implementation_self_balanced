package main;

import java.io.*;
import java.util.*;

/**
 * @author Ashish Bista
 * @since 11/16/21
 **/
public class PatientOperations {
    Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public PatientDetail patientDetailGetter() {

        PatientDetail patientDetail = new PatientDetail();
        System.out.println("Input Patient Name");
        patientDetail.setPatientName(scanner.nextLine());
        System.out.println("Doctor's Name");
        patientDetail.setDoctorName(scanner.nextLine());
        System.out.println("Input Appointment Date");
        patientDetail.setAppointmentDates(scanner.nextLine());
        return patientDetail;
    }

    BST loadBST() throws Exception {

        BST<PatientDetail> tree = new BST();
        BufferedReader br = new BufferedReader(new FileReader("./src/dbfile/Patient.txt"));
        String line;
//        List<PatientDetail> unbalancedList = new ArrayList();
        while ((line = br.readLine()) != null) {
            PatientDetail patientData = new PatientDetail();
            String[] data = line.split(",");
            patientData.setPatientName(data[0].trim());
            patientData.setDoctorName(data[1].trim());
            String appointmentDate = "";
            for (int i = 2; i < data.length; i++) {
                if (data.length - 1 == i)
                    appointmentDate = String.format("%s%s", appointmentDate, data[i].trim());
                else appointmentDate = String.format("%s%s,", appointmentDate, data[i].trim());

            }

            patientData.setAppointmentDates(appointmentDate);
            tree.insert(patientData);
//            unbalancedList.add(patientData);
        }
        br.close();
        return balanceTreeOnRequest(tree);
//        Collections.sort(unbalancedList);
//        BST.TreeNode treeNode = tree.sortedArrayToBST(unbalancedList,0,unbalancedList.size()-1);
//        tree.setRoot(treeNode);
//        return tree;
    }

    void saveNewPatient(BST tree) {
        System.out.println("NEw Patient");
        PatientDetail patientDetail = patientDetailGetter();
        tree.add(patientDetail);

    }

    BST balanceTreeOnRequest(BST ss) {
        BST tree = new BST();
        List items = new ArrayList(List.of(ss.toArray()));
        Collections.sort(items);
        BST.TreeNode treeNode = tree.sortedArrayToBST(items, 0, items.size() - 1);
        tree.setRoot(treeNode);
        /**
         * Balance Logic to add
         */
        return tree;
    }

    public void insertNewPatient(BST root) {
        System.out.println("Adding New Patient");
        PatientDetail patientDetail = patientDetailGetter();
        root.insert(patientDetail);
        DisplayAllPatients(root);
    }

    void DisplayAllPatients(BST root) {
        System.out.println("Displaying All Patients");
        for (Object patientDetail :
                List.of(root.toArray())) {
            System.out.println(((PatientDetail) patientDetail).toString());

        }

    }

    public void deletePatientInformation(BST root) throws Exception {
        System.out.println("Deleting Patient Info");
        PatientDetail patientDetail = new PatientDetail();
        System.out.println("Input Patient Name");
        patientDetail.setPatientName(scanner.nextLine());
        if (root.search(patientDetail))
            root.delete(patientDetail);
        throw new Exception("Details Not Found");
    }

    public void editInfo(BST root) throws Exception {
        System.out.println("Updating Patient Info");
        getAdditionalInfoFromPatientName(root);
        System.out.println("Insert Updated Details");
        PatientDetail patientDetail = patientDetailGetter();
        if (root.replace(patientDetail))
            System.out.println("Details Updated Successfully");
        ;
    }

    public PatientDetail getAdditionalInfoFromPatientName(BST root) throws Exception {
        PatientDetail patientDetail = new PatientDetail();
        System.out.println("Input Patient Name");
        patientDetail.setPatientName(scanner.nextLine());
        patientDetail = (PatientDetail) root.getElementDetail(patientDetail);
        System.out.println(patientDetail.toString());
        return patientDetail;
    }

    public void persistAll(BST root) throws IOException {
        FileWriter fw = new FileWriter("./src/dbfile/PatientSave.txt");
        fw.write(elementParser(List.of(root.toArray())));
        fw.close();
    }

    private String elementParser(List toArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object listElement :
                toArray) {
            PatientDetail patientDetail = (PatientDetail) listElement;
            stringBuilder.append(patientDetail.getPatientName()).append(",\t")
                    .append(patientDetail.getDoctorName()).append(",\t")
                    .append(patientDetail.getAppointmentDates()).append("\n");

        }
        return stringBuilder.toString();
    }
}
