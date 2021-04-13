package com.agiklo.HeathProject.service.workout;

import com.agiklo.HeathProject.mapper.WorkoutMapper;
import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.model.dto.WorkoutDTO;
import com.agiklo.HeathProject.model.workout.Workout;
import com.agiklo.HeathProject.repository.ApplicationUserRepository;
import com.agiklo.HeathProject.repository.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final ApplicationUserRepository applicationUserRepository;

    public List<WorkoutDTO> getAllWorkouts(Principal principal, Pageable pageable){
        ApplicationUser user = getLoginUser(principal);
        return workoutRepository.findWorkoutsByUser(user, pageable)
                .stream()
                .map(workoutMapper::mapWorkoutToDTO)
                .collect(Collectors.toList());
    }

    public Workout addNewWorkout(Workout workout, Principal principal) {
        ApplicationUser user = getLoginUser(principal);
        LocalDateTime actualTime = LocalDateTime.now();
        workout.setUser(user);
        workout.setDateOfWorkout(actualTime);
        return workoutRepository.save(workout);
    }

    public void deleteWorkoutById(Long id, Principal principal) {
        Workout workout = getWorkoutById(id);
        if(ApplicationUser.isAuthor(workout, principal)){
            workoutRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not owner of this workout");
        }
    }

    private ApplicationUser getLoginUser(Principal principal){
        return applicationUserRepository.findByEmail(principal.getName()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private Workout getWorkoutById(Long id){
        return workoutRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout does not exist"));
    }
}
