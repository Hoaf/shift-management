package shift.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shift.management.entity.User;
import shift.management.imp.service.ShiftServiceImp;
import shift.management.model.ShiftModel;
import shift.management.model.UpdateShiftModel;
import shift.management.service.ShiftService;
import shift.management.service.UserService;
import shift.management.util.Constant;
import shift.management.util.URL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(URL.API)
public class ShiftController {
    private static final Logger logger = Logger.getLogger(ShiftController.class);

    @Autowired
    ShiftService shiftService;

    //create schedule and shift
    @PostMapping(URL.CREATE_SCHEDULE_SHIFT)
    public ResponseEntity createScheduleAndShift (@RequestBody List<ShiftModel> list){
        logger.info(Constant.BEGIN_CONTROLLER + "createScheduleAndShift");
        try {

            return new ResponseEntity(shiftService.createShift(list), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "createScheduleAndShift");
        }
    }

    //update schedule and shift
    @PutMapping(URL.UPDATE_SCHEDULE_SHIFT)
    public ResponseEntity updateScheduleAndShift (@RequestBody List<UpdateShiftModel> list){
        logger.info(Constant.BEGIN_CONTROLLER + "updateScheduleAndShift");
        try {

            return new ResponseEntity(shiftService.updateShift(list), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "updateScheduleAndShift");
        }
    }

    //get list shift
    @GetMapping(URL.GET_LIST_SHIFT)
    public ResponseEntity getListShiftBySchedule (@RequestParam String dateStr){
        logger.info(Constant.BEGIN_CONTROLLER + "getListShiftBySchedule");
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            return new ResponseEntity(shiftService.getListShiftBySchedule(date), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
        }finally {
            logger.info(Constant.END_CONTROLLER + "getListShiftBySchedule");
        }
    }
}
