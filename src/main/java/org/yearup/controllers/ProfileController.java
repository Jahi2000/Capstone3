package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.models.Profile;

@RestController
@RequestMapping("profiles")
@CrossOrigin
public class ProfileController
{
    private ProfileDao profileDao;

    @Autowired
    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @PostMapping()
    @PreAuthorize("permitAll()")
    public Profile createProfile(@RequestBody Profile profile)
    {
        try
        {
            return profileDao.create(profile);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public Profile getById(@PathVariable int id)
    {
        try
        {
            var profile = profileDao.getById(id);

            if(profile == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return profile;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oh... something went wrong");
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("permitAll()")
    public void updateProfile(@PathVariable int id, @RequestBody Profile profile)
    {
        try
        {
            profileDao.update(id, profile);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oopsies we made a poopsies!");
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("permitAll()")
    public void deleteProfile(@PathVariable int id)
    {
        try
        {
            var profile = profileDao.getById(id);

            if(profile == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            profileDao.delete(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

}
