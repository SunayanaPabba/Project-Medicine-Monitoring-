package com.medicinemonitoring.medicinemonitoring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicinemonitoring.medicinemonitoring.models.BranchAdmin;
import com.medicinemonitoring.medicinemonitoring.models.Medicine;

@Repository
public interface MedicineDao extends CrudRepository<Medicine, Integer> {
	public Medicine findByMedicineId(int medicineId );
	public Medicine findByMedicineName(String medicineName);
	@Query("Select s from Medicine s where s.branchadmin=:admin")
	public List<Medicine> getMedicineByBranchId(@Param("admin") BranchAdmin admin);
	@Query("Select m from Medicine m where m.branchadmin=:badmin and  m.medicineId=:medicineId")
	public Medicine getBranchMedicineById(@Param("badmin") BranchAdmin badmin,@Param("medicineId") int medicineId);
	@Query("Select m from Medicine m where m.branchadmin=:badmin and m.type=:type")
	public List<Medicine> getByMedicineType(@Param("badmin")BranchAdmin badmin,@Param("type") String type);
	
	@Query("Select s from Medicine s where s.medicineName like :medicinename%")
	public List<Medicine> getMedicineByName(@Param("medicinename") String medicinename);
	@Query("Select m from Medicine m where m.type like :medicinetype%")
	public List<Medicine> getMedicineByType(@Param("medicinetype") String medicinetype);
	@Query("Select m from Medicine m where m.medicineFor Like :medicineillness%")
	public List<Medicine> getMedicineByIllness(@Param("medicineillness") String medicineillness);
	@Query("Select m from Medicine m where m.manufacturer Like :medicinebrand%")
	public List<Medicine> getMedicineByBrand(@Param("medicinebrand") String medicinebrand);
	
	@Query("select s from Medicine s where s.medicineName like :medicinename% or s.type like :medicinename% or s.medicineFor like :medicinename% or s.manufacturer like :medicinename%")
	public List<Medicine> getMedicineByAll(@Param("medicinename") String medicinename);
}
