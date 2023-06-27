package org.yearup.data;


import org.yearup.models.Profile;

public interface ProfileDao
{
    Profile create(Profile profile);
    Profile getById(int profileId);
    void update(int profileId, Profile profile);
    void delete(int profileId); //<- adding delete profile cuz why not? Who am I to deter someone from cleaning up their digital footprint.
}
