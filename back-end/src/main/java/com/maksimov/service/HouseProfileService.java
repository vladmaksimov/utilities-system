package com.maksimov.service;

import com.maksimov.entity.HouseProfile;

import java.util.List;

/**
 * Created on 17.04.2016.
 */
public interface HouseProfileService {

    HouseProfile createProfile();

    HouseProfile updateProfile(HouseProfile config);

    void deleteProfile(Long id);

    HouseProfile getProfile(Long id);

    List<HouseProfile> getUserProfiles(Long userId);

}
