/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.report_support_backend.record;

/**
 *
 * @author ferdi
 */
public record ApiResponse<T>(boolean success, String message, T data) {}