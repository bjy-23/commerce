package com.wondersgroup.commerce.teamwork.wywork.javabean;

public class StatisticsBean {
	public class Vo {
		String abbuseSt;
		String organId;
		String dailySt;
		String focusSt;
		String groupSt;
		String specialSt;
		String serviceSt;
		String total;

		public String getAbbuseSt() {
			return abbuseSt;
		}

		public void setAbbuseSt(String abbuseSt) {
			this.abbuseSt = abbuseSt;
		}

		public String getOrganId() {
			return organId;
		}

		public void setOrganId(String organId) {
			this.organId = organId;
		}

		public String getDailySt() {
			return dailySt;
		}

		public void setDailySt(String dailySt) {
			this.dailySt = dailySt;
		}

		public String getFocusSt() {
			return focusSt;
		}

		public void setFocusSt(String focusSt) {
			this.focusSt = focusSt;
		}

		public String getGroupSt() {
			return groupSt;
		}

		public void setGroupSt(String groupSt) {
			this.groupSt = groupSt;
		}

		public String getSpecialSt() {
			return specialSt;
		}

		public void setSpecialSt(String specialSt) {
			this.specialSt = specialSt;
		}

		public String getServiceSt() {
			return serviceSt;
		}

		public void setServiceSt(String serviceSt) {
			this.serviceSt = serviceSt;
		}

		public String getTotal() {
			return total;
		}

		public void setTotal(String total) {
			this.total = total;
		}

	}

	Vo organStatisticsVo;
	Vo userStatisticsVo;

	public Vo getOrganStatisticsVo() {
		return organStatisticsVo;
	}

	public void setOrganStatisticsVo(Vo organStatisticsVo) {
		this.organStatisticsVo = organStatisticsVo;
	}

	public Vo getUserStatisticsVo() {
		return userStatisticsVo;
	}

	public void setUserStatisticsVo(Vo userStatisticsVo) {
		this.userStatisticsVo = userStatisticsVo;
	}

}
