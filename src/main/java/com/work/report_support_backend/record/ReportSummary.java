/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.report_support_backend.record;

import com.work.report_support_backend.enumeration.Project;
import java.util.UUID;

/**
 *
 * @author ferdi
 */

public record ReportSummary(UUID uid, String partnerName, int supportTime, Project project) {
    
}
