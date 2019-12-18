package egovframework.com.paid.pet.cntc.igc.service;

import egovframework.com.uss.cmt.service.CmtManageVO;

/**
 * 출퇴근관리에 관한 인터페이스클래스를 정의한다.
 * @author 표준프레임워크 개발팀
 * @since 2014.08.29
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.08.29  개발팀          최초 생성
 *
 * </pre>
 */
public interface CorruptionService {

	/**
	 * 출퇴근정보 목록 화면에 출력
	 * @param  DeptInfo (부서별 - optional) 검색조건
	 * @return List<CmtManageVO> 업무사용자 목록정보
	 * @throws Exception
	 */
	public String selectGetCvplCnvrAt(CmtManageVO cmtSearchVO) throws Exception;



}