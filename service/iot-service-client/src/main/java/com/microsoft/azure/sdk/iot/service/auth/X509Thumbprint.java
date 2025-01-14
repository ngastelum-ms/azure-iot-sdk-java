// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.microsoft.azure.sdk.iot.service.auth;

import java.security.SecureRandom;

public class X509Thumbprint
{
    private String primaryThumbprint;
    private String secondaryThumbprint;

    //Thumbprints are made up of 40 hex characters
    private static final int THUMBPRINT_DIGIT_MAX = 16;
    private static final int THUMBPRINT_LENGTH = 40;

    /**
     * Constructor for an X509 Thumbprint that randomly generates the primary and secondary thumbprints
     */
    X509Thumbprint()
    {
        //Codes_SRS_X509THUMBPRINT_34_011: [This constructor shall generate a random primary and secondary thumbprint.]
        this.primaryThumbprint = generateValidThumbprint();
        this.secondaryThumbprint = generateValidThumbprint();
    }

    /**
     * Constructor for an X509 Thumbprint with the provided primary and secondary thumbprints
     * @param primaryThumbprint the primary thumbprint
     * @param secondaryThumbprint the secondary thumbprint
     * @throws IllegalArgumentException if the provided thumbprint is an invalid format
     */
    X509Thumbprint(String primaryThumbprint, String secondaryThumbprint)
    {
        this.primaryThumbprint = primaryThumbprint;
        this.secondaryThumbprint = secondaryThumbprint;
    }

    /**
     * Getter for the primary thumbprint
     * @return the primary thumbprint
     */
    String getPrimaryThumbprint()
    {
        return this.primaryThumbprint;
    }

    /**
     * Getter for the secondary thumbprint
     * @return the secondary thumbprint
     */
    String getSecondaryThumbprint()
    {
        return this.secondaryThumbprint;
    }

    /**
     * Setter for primary thumbprint
     * @param primaryThumbprint the thumbprint value to set
     * @throws IllegalArgumentException if the provided thumbprint is an invalid format
     */
    void setPrimaryThumbprint(String primaryThumbprint) throws IllegalArgumentException
    {
        this.primaryThumbprint = primaryThumbprint;
    }

    /**
     * Setter for secondary thumbprint
     * @param secondaryThumbprint the thumbprint value to set
     * @throws IllegalArgumentException if the provided thumbprint is an invalid format
     */
    void setSecondaryThumbprint(String secondaryThumbprint) throws IllegalArgumentException
    {
        this.secondaryThumbprint = secondaryThumbprint;
    }

    /**
     * Creates a valid, random thumbprint
     * @return the generated thumbprint
     */
    private String generateValidThumbprint()
    {
        StringBuilder thumbprint = new StringBuilder();
        SecureRandom rand = new SecureRandom(); //SecureRandom chooses its own seed, better than providing timestamp

        for (int i = 0; i < THUMBPRINT_LENGTH; i++)
        {
            thumbprint.append(Integer.toHexString(rand.nextInt(THUMBPRINT_DIGIT_MAX)));
        }

        return thumbprint.toString();
    }
}