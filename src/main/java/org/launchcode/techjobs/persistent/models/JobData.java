package org.launchcode.techjobs.persistent.models;

import java.util.ArrayList;

// This is a change made in sandbox.

/**
 * Created by LaunchCode
 */
public class JobData {


    /**
     * Returns the results of searching the Jobs data by field and search term.
     *
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * @param column Job field that should be searched.
     * @param value Value of the field to search for.
     * @param allJobs The list of jobs to search.
     * @return List of all jobs matching the criteria.
     */
    public static ArrayList<Job> findByColumnAndValue(String column, String value, Iterable<Job> allJobs) {

        ArrayList<Job> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){//if all return all jobs
            return (ArrayList<Job>) allJobs;
        }

        if (column.equals("all")){
            results = findByValue(value, allJobs);//if column is all search all fields
            return results;
        }
        for (Job job : allJobs) {

            String aValue = getFieldValue(job, column);//get value of the specified field

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(job);//add job to results if it matches the serch term
            }
        }

        return results;
    }

    public static String getFieldValue(Job job, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = job.getName();
        } else if (fieldName.equals("employer")){
            theValue = job.getEmployer().toString();
        } else {
            theValue = job.getSkills().toString();
        }

        return theValue;
    }

    /**
     * Search all Job fields for the given term.
     *
     * @param value The search term to look for.
     * @param allJobs The list of jobs to search.
     * @return      List of all jobs with at least one field containing the value.
     */
    public static ArrayList<Job> findByValue(String value, Iterable<Job> allJobs) {


        ArrayList<Job> results = new ArrayList<>();

        for (Job job : allJobs) {
//check if any field of the job contains the search term
            if (job.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            } else if (job.getEmployer().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            } else if (job.getSkills().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(job);//add job to results if it matches
            }

        }

        return results;
    }


}

