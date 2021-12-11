package main;

/**
 * @author Ashish Bista
 * @since 11/17/21
 **/
public class PatientDetail implements Comparable<PatientDetail> {

    private String patientName;
    private String doctorName;
    private String appointmentDates;


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentDates() {
        return appointmentDates;
    }

    public void setAppointmentDates(String appointmentDates) {
        this.appointmentDates = appointmentDates;
    }


    @Override
    public int compareTo(PatientDetail o) {
        if (this.patientName.compareTo(o.patientName)>0) {
            return 1;
        } else if (this.patientName.compareTo(o.patientName)==0) {
            return 0;
        } else return -1;
    }

    @Override
    public String toString() {
        return
                "patientName='" + patientName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", appointmentDates='" + appointmentDates + '\'' +
                '\n';
    }


}
