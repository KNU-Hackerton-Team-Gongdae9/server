package com.knu.community.profile.repository;

import com.knu.community.profile.domain.MyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<MyProfile, Long> {
}
