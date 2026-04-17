package com.pfe.model;

import com.pfe.dto.ActiveAnnouncementDto;
import com.pfe.dto.RecentCandidatureDto;
import com.pfe.dto.TechnologyStats;
import com.pfe.dto.UpcomingInterviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStats {
    private Long activeCandidatures;
    private Long plannedInterviews;
    private Long activeAnnouncements;
    private Double responseRate;
    private List<TechnologyStats> technologyStats;
    private List<RecentCandidatureDto> recentCandidatures;
    private List<UpcomingInterviewDto> upcomingInterviews;
    private List<ActiveAnnouncementDto> activeAnnouncementsList;
}

