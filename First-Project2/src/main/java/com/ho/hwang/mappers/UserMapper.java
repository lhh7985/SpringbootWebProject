package com.ho.hwang.mappers;

import java.util.List;

import com.ho.hwang.dto.Activity.*;
import com.ho.hwang.dto.Customer.*;
import com.ho.hwang.dto.Employee.*;
import com.ho.hwang.dto.ManagerHistory.UpdateManagerHistoryDTO;
import com.ho.hwang.dto.Product.*;
import com.ho.hwang.dto.Sr.InsertSrDTO;
import com.ho.hwang.dto.Sr.SelectSrDTO;
import com.ho.hwang.dto.Sr.SelectSrDetailDTO;
import com.ho.hwang.dto.Sr.SelectSrListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ho.hwang.account.Account;
import com.ho.hwang.vo.CodeVO;
import com.ho.hwang.vo.CustomerListVO;
import com.ho.hwang.vo.ManagerVO;
import com.ho.hwang.vo.OsVO;

@Repository
@Mapper
public interface UserMapper {
	
	Account selectUser(String id);
	void save(Account account);
	

	List<CustomerListVO> selectCustomer();
	List<SelectCustomerListDTO> selectCustomerList();
	
	List<SelectDeliveryDTO> selectDelivery(int co_id);
	
	List<OsVO> selectOS(int co_id);
	List<SelectTotalOsDTO> selectTotalOS();


	SelectCustomerDetailDTO selectCustomerDetail(int co_id);
	SelectEmployeeDTO selectEmployee(int employee_id);
	
	List<SelectCustomerProductDTO> selectCustomerProduct(int customer_id);
	
	
	
	
	void insertEmployee(InsertEmployeeDTO insertEmployeeDTO);
	void insertSR(InsertSrDTO insertSrDTO);
	
	//이름으로 아이디값 찾기
	int selectCode(String name);
	int selectDept(String dept_name);
	int selectCustomerID(String name);
	int selectProductID(String pname);
	
	//담당자 이름 찾기
	String selectSE(int id);
	String selectSALES(int id);
	String selectName(String name);
	
	
	
	
	List<SelectEmployeeSecuveDTO> selectEmployee_secuve();
	
	List<SelectEmployeeOtherDTO> selectEmployee_other();
	
	List<String> selectEmployee_type();
	List<String> selectDept_name();
	
	
	
	
	void insertCustomer(InsertCustomerDTO insertCustomerDTO);
	void insertManagerHistory(UpdateManagerHistoryDTO updateManagerHistoryDTO);
	
	int selectCustomer_id();
	
	void insertAddress(InsertCustomerDTO insertCustomerDTO);
	void insertActivity(InsertActivityDTO activityVO);
	
	
	
	List<SelectSrDTO> selectSR();
	List<SelectSrListDTO> selectSRList(int customer_id);
	SelectSrDetailDTO selectSRDetail(int sr_id);


	//고객사 방문내역확인
	List<SelectVisitDTO> selectVisit(int customer_id);

	//모든 회사 납품정보 확인
	List<SelectTotalDeliveryDTO> selectTotalDelivery();
	
	//모든 활동 확인
	List<SelectActivityDTO> selectActivity();
	
	//활동 완료
	void updateComplete(int activity_id);
	String selectComplete(int activity_id);
	
	//각 고객사별 활동 등록 과 검색
	void insertCustomerActivity(InsertCustomerActivityDTO activityVO);
	List<SelectCustomerActivityDTO> selectCustomerActivity(int sr_id);
	
	
	//제품 페이지 제품 검색
	List<SelectProductDTO> selectProduct();
	List<SelectProductDTO> selectSearchAllProduct();
	void insertDelivery(InsertDeliveryDTO insertDeliveryDTO);
	int selectDelivery_id();
	void insertOS(InsertDeliveryDTO insertDeliveryDTO);
	
	
	//관리자 페이지
	List<CodeVO> selectCodeList();
	//코드테이블 삭제
	void deleteCode(String code_id);
	//코드테이블 추가
	void insertCode(CodeVO codevo);
	
	//고객사 리스트 삭제
	void deleteCustomer(String customer_id);
	
	//납품정보 삭제
	void deleteDelivery(String delivery_id);
	
	//직원 삭제
	void deleteEmployee(String employee_id);
	
	
	//고객사 정보 수정
	void updateEnddate(UpdateManagerHistoryDTO updateManagerHistoryDTO);
	void updateManager(UpdateCustomerDetailDTO updateCustomerDetailDTO);
	void updateSE(UpdateCustomerDetailDTO updateCustomerDetailDTO);
	void updateSales(UpdateCustomerDetailDTO updateCustomerDetailDTO);
	
	//담당자이력
	List<ManagerVO> selectManager(int customer_id);

}
